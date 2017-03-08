package com.jbirdvegas.groovy.utils.utilities

import com.jbirdvegas.groovy.utils.annotations.ClassMagic
import com.jbirdvegas.groovy.utils.internal.Applier
import org.apache.commons.lang3.exception.ExceptionUtils

@ClassMagic
class ExceptionClassUtils implements Applier {
    @Override
    void applyAll() {
        addPrettyPrintStacktraces()
    }

    static def addPrettyPrintStacktraces() {
        Exception.metaClass.prettyStack = { ->
            ExceptionUtils.getStackTrace(delegate as Throwable)
        }
    }
}
