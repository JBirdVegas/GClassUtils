package com.jbirdvegas.groovy.utils.utilities

import com.jbirdvegas.groovy.utils.annotations.ClassMagic
import com.jbirdvegas.groovy.utils.internal.Applier

@ClassMagic
class DoubleClassUtils implements Applier {
    @Override
    void applyAll() {
        addRoundToDouble()
    }

    static addRoundToDouble() {
        Double.metaClass.getRound = { ->
            Math.round(delegate as Double)
        }
    }
}
