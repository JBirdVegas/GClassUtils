package com.jbirdvegas.groovy.utils

import com.jbirdvegas.groovy.utils.utilities.StringClassUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestStringClassUtils {
    @Before
    void setup() {
        StringClassUtils.addStringToFileUtils()
        StringClassUtils.addStringManipulationUtils()
    }

    @Test
    void testBase64() {
        Assert.assertTrue("Base64 encoding did not produce expected result", "hello world in Base64".base64 == 'aGVsbG8gd29ybGQgaW4gQmFzZTY0')
        def tempFile = File.createTempFile("pre", ".post")
        "test".writeToFile(tempFile)
        Assert.assertTrue(tempFile.text == "test")
    }

    @Test
    void testWriteToFile() {
        def tempFile = File.createTempFile("pre", ".post")
        "test".writeToFile(tempFile)
        Assert.assertTrue(tempFile.text == "test")
        tempFile.delete()
    }

    @Test
    void testAppendToFile() {
        def tempFile = File.createTempFile("pre", ".post")
        "test".writeToFile(tempFile)
        "testing-again".appendToFile(tempFile)
        Assert.assertTrue(tempFile.text == "test\ntesting-again")
        tempFile.delete()
    }
}
