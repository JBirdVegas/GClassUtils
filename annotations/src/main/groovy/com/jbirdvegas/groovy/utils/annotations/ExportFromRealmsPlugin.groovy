package com.jbirdvegas.groovy.utils.annotations;

//import org.gradle.api.Plugin
//import org.gradle.api.Project
//import org.gradle.api.tasks.compile.JavaCompile

@SuppressWarnings("GroovyUnusedDeclaration")
class ExportFromRealmsPlugin {//implements Plugin<Project> {
    public static final String REPO_SNAPSHOTS = 'http://10.3.52.59:8081/nexus/content/repositories/AMA-Snapshots'
    public static final String REPO_RELEASE = 'http://10.3.52.59:8081/nexus/content/repositories/AMA-Release'
    public static final String ANNOTATION_GROUP_N_ID = 'com.asurion.ama.coriolis:annotation'
    public static final String ANNOTATION_NOTATION = "$ANNOTATION_GROUP_N_ID:1.+"
    public static
    final String PROCESSOR_CLASSPATH = "com.asurion.ama.coriolis.annotation.processor.ClassMagicProcessor"

//    @Override
//    void apply(Project project) {
//        if (!hasAnnotationsInClassPath(project)) {
//            project.logger.lifecycle("$project.name: Annotation processor not found on classpath, not running ExportFromRealms annotation processor")
//        }
//
//        // make sure the project has minimal requirements to run the annotation processor
//        ensureProjectSetup(project)
//
//        def process = project.tasks.create('process', JavaCompile)
//        def sourceSet = project.sourceSets.main
//
//        // our annotation processor needs all the java source to detect annotated Types
//        process.source = sourceSet.allJava
//        // We always have to do this work... no rest for the wicked
//        process.outputs.upToDateWhen { false }
//        // just use the project's compilation classpath
//        process.classpath = project.configurations.compile
//        // set the output of our task to be `generated-sources`
//        process.destinationDir = new File((++sourceSet.java.srcDirs.iterator() as File).parentFile,
//                'generated-sources')
//        // notify the annotation processor of our project's configurations
//        process.options.compilerArgs += [
//                "-proc:only",
//                "-processor", PROCESSOR_CLASSPATH,
//                "-Aproject.path=$project.projectDir.absolutePath",
//                "-Aproject.name=${project.name.replaceAll(":", "-")}"
//        ]
//
//        def compileTask = project.tasks.getByName("compileJava")
//        // add the output of our task (generated source code) as a source input item
//        // to the standard java compiler task used to compile the rest of the code
//        compileTask.source += process.destinationDir
//        // set the normal java compiler to depend on our source generation
//        compileTask.dependsOn process
//    }

//    boolean ensureProjectSetup(Project project) {
//        // Add AMA-Release repository to build script's dependencies if it's not already there
//        def buildRelease = project.buildscript.repositories.find { (REPO_RELEASE == it.url as String) }
//        if (!buildRelease) {
//            project.buildscript.repositories { maven { url REPO_RELEASE } }
//        }
//
//        // Add AMA-Snapshots repository to project's dependencies if it's not already there
//        def buildSnapshot = project.buildscript.repositories.find { (REPO_SNAPSHOTS == it.url as String) }
//        if (!buildSnapshot) {
//            project.buildscript.repositories { maven { url REPO_SNAPSHOTS } }
//        }
//
//        // Add AMA-Release repository to project's dependencies if it's not already there
//        def releaseRepo = project.repositories.find { (REPO_RELEASE == it.url as String) }
//        if (!releaseRepo) {
//            project.repositories { maven { url REPO_RELEASE } }
//        }
//        // Add AMA-Snapshots repository to project's dependencies if it's not already there
//        def snapshotRepo = project.repositories.find { REPO_SNAPSHOTS == it.url as String }
//        if (!snapshotRepo) {
//            project.repositories { maven { url REPO_SNAPSHOTS } }
//        }
//        return hasAnnotationsInClassPath(project)
//    }
//
//    static boolean hasAnnotationsInClassPath(Project project) {
//        // Check if the realm has our annotation package as a dependency
//        def compileConfiguration = project.configurations.getByName('compile')
//        if (compileConfiguration.allDependencies.collect {
//            ANNOTATION_GROUP_N_ID == ("$it.group:$it.name" as String)
//        }.size() == 0) {
//            // no annotation package dependency found... add it in non strict way.
//            project.dependencies.add('compile', ANNOTATION_NOTATION) {
//                project   // Projects with a larger version annotation dependency will win, overriding
//                // this version entirely.  It's possible for this to be the case when
//                // annotations package is imported through a transitive dependency
//                it.force = false
//            }
//        }
//    }
}
