package com.jbirdvegas.groovy.utils.utilities

import com.jbirdvegas.groovy.utils.annotations.ClassMagic
import com.jbirdvegas.groovy.utils.internal.Applier
import com.jbirdvegas.groovy.utils.internal.Utils
import org.apache.commons.lang3.StringEscapeUtils

@ClassMagic
class StringClassUtils implements Applier {
    @Override
    void applyAll() {
        addStringToFileUtils()
        addStringManipulationUtils()
        addStringEncodingUtils()
        addStringPostMethods()
        addStringPatchMethods()
        addStringPutMethods()
    }

    static def addStringPostMethods() {
        String.metaClass.post = { String body, Map<String, Object> headers ->
            Utils.Http.post(delegate as String, body, headers)
        }

        String.metaClass.post = { String body ->
            Utils.Http.post(delegate as String, body, null)
        }
    }

    static def addStringPatchMethods() {
        String.metaClass.patch = { String body, Map<String, Object> headers ->
            Utils.Http.patch(delegate as String, body, headers)
        }

        String.metaClass.patch = { String body ->
            Utils.Http.patch(delegate as String, body, null)
        }
    }

    static def addStringPutMethods() {
        String.metaClass.put = { String body, Map<String, Object> headers ->
            Utils.Http.put(delegate as String, body, headers)
        }

        String.metaClass.put = { String body ->
            Utils.Http.put(delegate as String, body, null)
        }
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

    static def addStringEncodingUtils() {
        String.metaClass.encodeToHtml4 = { ->
            StringEscapeUtils.escapeHtml4(delegate as String)
        }

        String.metaClass.encodeToHtml3 = { ->
            StringEscapeUtils.escapeHtml3(delegate as String)
        }

        String.metaClass.encodeToHtml = { ->
            StringEscapeUtils.escapeHtml4(delegate as String)
        }
    }
}
