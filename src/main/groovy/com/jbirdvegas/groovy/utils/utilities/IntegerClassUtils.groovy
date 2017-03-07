package com.jbirdvegas.groovy.utils.utilities

import com.jbirdvegas.groovy.utils.annotations.ClassMagic
import com.jbirdvegas.groovy.utils.internal.Applier
import org.apache.commons.lang3.RandomStringUtils

import java.util.concurrent.TimeUnit

@ClassMagic
class IntegerClassUtils implements Applier {
    @Override
    void applyAll() {
        addTimeUtils()
        addToStringMethods()
        addMathUtils()
    }

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

    static void addToStringMethods() {
        Integer.metaClass.randomChars = { ->
            RandomStringUtils.randomAlphabetic(delegate as Integer)
        }

        Integer.metaClass.randomString = { ->
            RandomStringUtils.randomAscii(delegate as Integer)
        }
    }

    static void addMathUtils() {
        Integer.metaClass.half = { ->
            ((delegate as Integer) / 2).toInteger()
        }

        Integer.metaClass.fourth = { ->
            ((delegate as Integer) / 4).toInteger()
        }

        Integer.metaClass.double = { ->
            ((delegate as Integer) * 2).toInteger()
        }

        Integer.metaClass.quadruple = { ->
            ((delegate as Integer) * 4).toInteger()
        }
    }
}
