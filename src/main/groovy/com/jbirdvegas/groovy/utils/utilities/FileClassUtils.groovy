package com.jbirdvegas.groovy.utils.utilities

import com.jbirdvegas.groovy.utils.annotations.ClassMagic
import com.jbirdvegas.groovy.utils.internal.Applier

@ClassMagic
class FileClassUtils implements Applier {
    @Override
    void applyAll() {
        addMkParents()
    }

    static def addMkParents() {
        File.metaClass.mkparents = { ->
            if (!(delegate as File).parentFile.exists() && !(delegate as File).parentFile.mkdirs()) {
                throw new IOException("Failed to make parent directory at ${(delegate as File).parentFile.absolutePath}")
            }
        }

        File.metaClass.notContains = { String lookup ->
            !(delegate as File).text.contains(lookup)
        }

        File.metaClass.contains = { String lookup ->
            (delegate as File).text.contains(lookup)
        }
    }
}
