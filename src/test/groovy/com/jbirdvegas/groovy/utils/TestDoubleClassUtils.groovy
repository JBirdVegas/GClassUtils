package com.jbirdvegas.groovy.utils

import com.jbirdvegas.groovy.utils.utilities.DoubleClassUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestDoubleClassUtils {
    @Before
    void setup() {
        DoubleClassUtils.addRoundToDouble()
    }

    @Test
    void testRoundingDown() {
        Assert.assertTrue(3.1D.round() == 3)
        Assert.assertTrue(3.45D.round() == 3)
    }

    @Test
    void testRoundingUp() {
        Assert.assertTrue(3.5D.round() == 4)
        Assert.assertTrue(3.99999D.round() == 4)
    }
}
