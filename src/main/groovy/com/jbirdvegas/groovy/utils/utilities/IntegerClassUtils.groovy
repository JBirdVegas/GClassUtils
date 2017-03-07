package com.jbirdvegas.groovy.utils.utilities

import com.jbirdvegas.groovy.utils.annotations.ClassMagic
import com.jbirdvegas.groovy.utils.internal.Applier

import java.util.concurrent.TimeUnit

@ClassMagic
class IntegerClassUtils implements Applier {
    static def addTimeUtils() {
        Integer.metaClass.getSeconds = { ->
            TimeUnit.SECONDS.toMillis(delegate as Long)
        }

        Integer.metaClass.getMinutes = { ->
            TimeUnit.MINUTES.toMillis(delegate as Long)
        }

        Integer.metaClass.getHours = { ->
            TimeUnit.HOURS.toMillis(delegate as Long)
        }

        Integer.metaClass.getDays = { ->
            TimeUnit.DAYS.toMillis(delegate as Long)
        }
    }

    @Override
    void applyAll() {
        addTimeUtils()
    }
}
