package com.jbirdvegas.groovy.utils

import com.jbirdvegas.groovy.utils.utilities.IntegerClassUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestIntegerClassUtils {

    @Before
    void setup() {
        IntegerClassUtils.addTimeUtils()
    }

    @Test
    void testToSeconds() {
        Assert.assertTrue(1.seconds == 1000)
        Assert.assertTrue(10.seconds == 10000)
        Assert.assertTrue(100.seconds == 100000)
    }
}
