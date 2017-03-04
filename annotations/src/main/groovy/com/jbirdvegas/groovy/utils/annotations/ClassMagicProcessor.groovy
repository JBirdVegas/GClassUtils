package com.jbirdvegas.groovy.utils.annotations

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.ParameterizedTypeName
import com.squareup.javapoet.TypeSpec

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.type.TypeMirror

// let javac know what annotations we want to process
@SuppressWarnings([ "unused", "WeakerAccess" , "UnnecessaryQualifiedReference"])
@SupportedAnnotationTypes(ClassMagicProcessor.CLASS_MAGIC_ANNOTATION)
class ClassMagicProcessor extends AbstractProcessor {
    static final String CLASS_MAGIC_ANNOTATION = "com.jbirdvegas.groovy.utils.annotations.ClassMagic"
    private static final boolean DEBUG_EXPORTED_PACKAGES = true
    private static final String OUTPUT_PATH_FORMAT = "%s/src/main/generated-sources/%s/%s/ClassMagic.java"
    private List<String> mAnnotatedPackages
    private String mProjectPath
    private String mProjectName

    ClassMagicProcessor() {
        super()
    }

    @Override
    synchronized void init(ProcessingEnvironment env) {
        mAnnotatedPackages = new ArrayList<>()
        mProjectPath = env.getOptions().get("project.path")
        mProjectName = env.getOptions().get("project.name")
    }

    // use overridden method instead of annotation to allow any version of java to access annotation processor
    @Override
    SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported()
    }

    @Override
    boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            System.out.println(mProjectName + ": Annotation processing is complete.  Writing " + mAnnotatedPackages.size() + " unique packages names for export to realms")
            try {
                createJavaClass()
            } catch (IOException e) {
                e.printStackTrace()
            }
            return true
        }
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(ClassMagic.class)
        if (DEBUG_EXPORTED_PACKAGES) {
            System.out.printf("Project %s contains %d classes annotated for export to realms%n", mProjectName, annotatedElements.size())
        }

        // discover services from the current compilation sources
        for (Element annotatedClass : annotatedElements) {
            ClassMagic annotation = annotatedClass.getAnnotation(ClassMagic.class)
            if (annotation == null) {
                // input is malformed, ignore
                continue
            }
            if (!annotation.enabled()) {
                // annotation is disabled. just skip it
                continue
            }
            TypeMirror typeMirror = annotatedClass.asType()
            String typeClass = typeMirror.toString()
            if (!mAnnotatedPackages.contains(typeClass)) {
                mAnnotatedPackages.add(typeClass)
            }
        }
        return false
    }

    void createJavaClass() throws IOException {
        Collections.sort(mAnnotatedPackages)
        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder("ClassMagic")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addJavadoc('$S\n', 'DO NOT MODIFY; this class is written automatically by the compiler')
                .addMethod(createPrivateConstructor())

        ParameterizedTypeName listOfClasses = getArrayListOfClasses()
        ClassName arrayListClassName = ClassName.get("java.util", "ArrayList")
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("getMagic")
                .returns(listOfClasses)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .addJavadoc('$S\n', 'DO NOT MODIFY; this method is written automatically by the compiler')
                .addStatement("\$T result = new \$T<>(${mAnnotatedPackages.size()})", listOfClasses, arrayListClassName)

        mAnnotatedPackages.each {
            int lastDot = it.lastIndexOf('.')
            String clazzName = it.substring(lastDot + 1)
            String found = it.substring(0, lastDot)
            methodBuilder.addStatement('result.add($L.class)', getTypeName(found, clazzName))
        }

        methodBuilder.addStatement("return result")
        typeBuilder.addMethod(methodBuilder.build())

        String packageName = "com.jbirdvegas.groovy.utils"
        // create a java file writer
        JavaFile.Builder builder = JavaFile.builder(packageName + "." + mProjectName, typeBuilder.build())
        // don't import java.lang.* it's redundant
        builder.skipJavaLangImports(true)
        // use four spaces for indent instead of default two spaces
        builder.indent("    ")
        // create the file in memory
        JavaFile javaFile = builder.build()

        File file = getBuildConfigFile(packageName)
        // ensure file structure
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            throw new IOException("Failed to create directory structure for " + file.getAbsolutePath())
        }

        javaFile.writeTo(file)
    }

    private static ParameterizedTypeName getArrayListOfClasses() {
        return ParameterizedTypeName.get(ClassName.get("java.util", "List"), ClassName.get(Class.class))
    }

    /**
     * Removes a paramaterization from an annotated class's name (as seen by the annotation processors
     *
     * @param clazzName name of class, possibly with a \<\T> paramaterization
     * @return clazzName that would conform to #getSimpleName()
     */
    private ClassName getTypeName(String packageName, String clazzName) {
        return ClassName.get(packageName, clazzName.contains("<") && clazzName.contains(">")
                ? clazzName.substring(0, clazzName.indexOf("<"))
                : clazzName)
    }

    File getBuildConfigFile(String group) {
        return new File(String.format(OUTPUT_PATH_FORMAT, mProjectPath, group.replaceAll("\\.", "/"), mProjectName))
    }

    private MethodSpec createPrivateConstructor() {
        return MethodSpec.constructorBuilder().addModifiers(Modifier.PRIVATE).build()
    }

    private String getStringFormatter(Class clazz) {
        //noinspection Duplicates
        //noinspection GroovyFallthrough
        switch (clazz.getSimpleName().toLowerCase()) {
            case "string":
                // causes the formatter used to wrap the value in quotes correctly
            case "date":
                // date objects are serialized to a string
                return '$S'
            case "long":
                return '$LL'
            case "double":
                return '$LD'
            case "float":
                return '$LF'
            default:
                // for the reset use literal
                return '$L'
        }
    }
}