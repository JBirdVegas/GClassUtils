package com.jbirdvegas.groovy.utils

import com.jbirdvegas.groovy.utils.utilities.ListClassUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestListClassUtils {
    @Before
    void setup() {
        ListClassUtils.addInputOutputStreams()
    }

    @Test
    void testByteArrayToInputStream() {
        def bytes = [12, 21, 23, 32, 34, 43]
        Assert.assertTrue(bytes.inputStream instanceof ByteArrayInputStream)
        Assert.assertTrue(((bytes.inputStream as ByteArrayInputStream).bytes) == bytes as byte[])
    }
}
