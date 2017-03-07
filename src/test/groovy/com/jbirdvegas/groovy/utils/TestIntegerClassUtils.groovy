package com.jbirdvegas.groovy.utils

import com.jbirdvegas.groovy.utils.utilities.IntegerClassUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import java.util.concurrent.TimeUnit

class TestIntegerClassUtils {

    @Before
    void setup() {
        new IntegerClassUtils().applyAll()
    }

    @Test
    void testToSeconds() {
        Assert.assertTrue(1.seconds == 1000)
        Assert.assertTrue(10.seconds == 10000)
        Assert.assertTrue(100.seconds == 100000)
    }

    @Test
    void testToMinutes() {
        Assert.assertTrue(1.minutes == TimeUnit.MINUTES.toMillis(1))
        Assert.assertTrue(10.minutes == TimeUnit.MINUTES.toMillis(10))
        Assert.assertTrue(100.minutes == TimeUnit.MINUTES.toMillis(100))
    }

    @Test
    void testToHours() {
        Assert.assertTrue(1.hours == TimeUnit.HOURS.toMillis(1))
        Assert.assertTrue(10.hours == TimeUnit.HOURS.toMillis(10))
        Assert.assertTrue(100.hours == TimeUnit.HOURS.toMillis(100))
    }

    @Test
    void testToDays() {
        Assert.assertTrue(1.days == TimeUnit.DAYS.toMillis(1))
        Assert.assertTrue(10.days == TimeUnit.DAYS.toMillis(10))
        Assert.assertTrue(100.days == TimeUnit.DAYS.toMillis(100))
    }

    @Test
    void testRandomChars() {
        String chars = 32.randomChars()
        Assert.assertTrue(chars.length() == 32)
        Assert.assertTrue(chars instanceof String)
        println "Chars: $chars"
    }

    @Test
    void testRandomString() {
        String chars = 32.randomString()
        Assert.assertTrue(chars.length() == 32)
        Assert.assertTrue(chars instanceof String)
        println "String: $chars"
    }

    @Test
    void testHalf() {
        Assert.assertTrue(12.half() == 6)
        Assert.assertTrue(24.half() == 12)
        Assert.assertTrue(48.half() == 24)
        Assert.assertTrue(51.half() == 25)
    }

    @Test
    void testFourth() {
        Assert.assertTrue(12.fourth() == 3)
        Assert.assertTrue(24.fourth() == 6)
        Assert.assertTrue(100.fourth() == 25)
        Assert.assertTrue(125.fourth() == 31)
    }

    @Test
    void testDouble() {
        Assert.assertTrue(12.double() == 24)
        Assert.assertTrue(10.double() == 20)
        Assert.assertTrue(100.double() == 200)
        Assert.assertTrue(1.double() == 2)
    }

    @Test
    void testQuadruple() {
        Assert.assertTrue(12.quadruple() == 48)
        Assert.assertTrue(48.quadruple() == 192)
        Assert.assertTrue(1.quadruple() == 4)
        Assert.assertTrue(2.quadruple() == 8)
    }
}
