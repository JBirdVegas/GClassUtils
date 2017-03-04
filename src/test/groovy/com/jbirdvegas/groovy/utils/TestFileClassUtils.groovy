package com.jbirdvegas.groovy.utils

import com.jbirdvegas.groovy.utils.utilities.FileClassUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestFileClassUtils {
    public static final File parentFile = File.createTempFile("abcdefg", "zxy").parentFile

    @Before
    void setup() {
        FileClassUtils.addMkParents()
    }

    @Test
    void testFileMkParents() {
        def firstFakePath = new File("$parentFile", "blah")
        def fakePath = new File("$firstFakePath.absolutePath/blah", "blah")
        fakePath.mkparents()
        Assert.assertTrue(fakePath.parentFile.exists() && fakePath.parentFile.isDirectory())
        Assert.assertTrue(firstFakePath.deleteDir() && !firstFakePath.exists())
    }
}
