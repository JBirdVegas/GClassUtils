package com.jbirdvegas.groovy.utils

import com.jbirdvegas.groovy.utils.utilities.StringClassUtils
import groovy.json.JsonSlurper
import org.apache.commons.lang3.StringEscapeUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TestStringClassUtils {
    @Before
    void setup() {
        new StringClassUtils().applyAll()
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

    @Test
    void testEncodeToHtml() {
        Assert.assertTrue("<script>alert('xxs')</script>".encodeToHtml() == "&lt;script&gt;alert('xxs')&lt;/script&gt;")
    }

    @Test
    void testEncodeToHtml3() {
        Assert.assertTrue(StringEscapeUtils.escapeHtml3("<script>alert('xxs')</script>") == "&lt;script&gt;alert('xxs')&lt;/script&gt;")
    }

    @Test
    void testEncodeToHtml4() {
        Assert.assertTrue("<script>alert('xxs')</script>".encodeToHtml4() == "&lt;script&gt;alert('xxs')&lt;/script&gt;")
    }

    @Test
    void testPostWithNoHeaders() {
        def response = 'http://httpbin.org/post'.post('blah=blah')
        def json = new JsonSlurper().parseText(response as String)
        Assert.assertTrue(json.form == ['blah': 'blah'])
    }

    @Test
    void testPostWithHeaders() {
        def response = 'http://httpbin.org/post'.post('{"blah":"blah"}', [
                'User-Agent'  : 'curl/7.52.1',
                'Accept'      : '*/*',
                'Content-Type': 'application/json'
        ])
        def json = new JsonSlurper().parseText(response as String)
        Assert.assertTrue(json.data == '{"blah":"blah"}')
    }

    @Test
    void testPatchWithNoHeaders() {
        def response = 'http://httpbin.org/patch'.patch('blah=blah')
        def json = new JsonSlurper().parseText(response as String)
        Assert.assertTrue(json.form == ['blah': 'blah'])
    }

    @Test
    void testPatchWithHeaders() {
        def response = 'http://httpbin.org/patch'.patch('{"blah":"blah"}', [
                'User-Agent'  : 'curl/7.52.1',
                'Accept'      : '*/*',
                'Content-Type': 'application/json'
        ])
        def json = new JsonSlurper().parseText(response as String)
        Assert.assertTrue(json.data == '{"blah":"blah"}')
    }

    @Test
    void testPutWithNoHeaders() {
        def response = 'http://httpbin.org/put'.put("blah=blah")
        def json = new JsonSlurper().parseText(response as String)
        Assert.assertTrue(json.data == 'blah=blah')
    }

    @Test
    void testPutWithHeaders() {
        def response = 'http://httpbin.org/put'.put('{"blah":"blah"}', [
                'User-Agent'  : 'curl/7.52.1',
                'Accept'      : '*/*',
                'Content-Type': 'application/json'
        ])
        def json = new JsonSlurper().parseText(response as String)
        Assert.assertTrue(json.data == '{"blah":"blah"}')
    }
}
