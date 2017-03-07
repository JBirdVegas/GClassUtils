package com.jbirdvegas.groovy.utils

import com.jbirdvegas.groovy.utils.utilities.IntegerClassUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import java.util.concurrent.TimeUnit

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
}
