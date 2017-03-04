package com.jbirdvegas.groovy.utils

import com.jbirdvegas.groovy.utils.utilities.ObjectClassUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestObjectClassUtils {
    TestModel model

    @Before
    void setup() {
        ObjectClassUtils.addJsonUtils()
        model = new TestModel(something: "hello world", someNumber: 1)
    }

    @Test
    void testPrettyJson() {
        Assert.assertTrue(model.json() == '{"something":"hello world","someNumber":1}')
    }

    @Test
    void testDenseJson() {
        def pretty = """{
  "something": "hello world",
  "someNumber": 1
}"""
        Assert.assertTrue(model.jsonPretty() == pretty)
    }

    static class TestModel {
        String something
        int someNumber
    }
}
