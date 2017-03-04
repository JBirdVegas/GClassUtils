package com.jbirdvegas.groovy.utils.utilities

import com.jbirdvegas.groovy.utils.internal.Applier

class StringClassUtils implements Applier {
    @Override
    void applyAll() {
        addStringToFileUtils()
        addStringManipulationUtils()
    }

    static def addStringToFileUtils() {
        String.metaClass.writeToFile = { File file ->
            if (!file.parentFile.exists() && !file.parentFile.mkdirs()) {
                throw new IOException("Failed to create parent directory at $file.absolutePath")
            }
            file.text = delegate
        }

        String.metaClass.appendToFile = { File file ->
            if (!file.parentFile.exists() && !file.parentFile.mkdirs()) {
                throw new IOException("Failed to create parent directory at $file.absolutePath")
            }
            file.text = "$file.text\n$delegate"
        }
    }

    static def addStringManipulationUtils() {
        String.metaClass.getBase64 = { ->
            (delegate as String).bytes.encodeBase64().toString()
        }
    }
}
