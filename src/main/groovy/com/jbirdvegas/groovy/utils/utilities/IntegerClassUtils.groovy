package com.jbirdvegas.groovy.utils.utilities

import com.jbirdvegas.groovy.utils.internal.Applier

class IntegerClassUtils implements Applier {
    static def addTimeUtils() {
        Integer.metaClass.getSeconds = { ->
            delegate * 1000
        }
    }

    @Override
    void applyAll() {
        addTimeUtils()
    }
}
