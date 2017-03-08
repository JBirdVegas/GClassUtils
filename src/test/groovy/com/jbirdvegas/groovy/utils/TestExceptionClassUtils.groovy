package com.jbirdvegas.groovy.utils

import com.jbirdvegas.groovy.utils.utilities.ExceptionClassUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestExceptionClassUtils {
    @Before
    void setup() {
        new ExceptionClassUtils().applyAll()
    }

    @Test
    void testExceptionPrettyStack() {
        def str = new Exception("hello").prettyStack() as String
        Assert.assertFalse(str.isEmpty())
        Assert.assertTrue(str.startsWith('java.lang.Exception: hello'))
        Assert.assertTrue(str.contains('\n\tat com.jbirdvegas.groovy.utils.TestExceptionClassUtils.testException(TestExceptionClassUtils.groovy:'))

        def second = new IllegalAccessException("goodbye").prettyStack() as String
        Assert.assertFalse(second.isEmpty())
        Assert.assertTrue(second.startsWith('java.lang.IllegalAccessException: goodbye'))
        Assert.assertTrue(second.contains('\n\tat com.jbirdvegas.groovy.utils.TestExceptionClassUtils.testException(TestExceptionClassUtils.groovy:'))
    }
}
