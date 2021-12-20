package com.borek.aoc.day

import junit.framework.TestCase
import org.junit.Test

class Day6Test : TestCase() {

    @Test
    fun testRunFirstExample() {
        val day = Day6("3,4,3,1,2")
        val answer = day.runFirst()
        assertEquals("5934", answer)
    }

    @Test
    fun testRunSecondExample() {
        val day = Day6("3,4,3,1,2")
        val answer = day.runSecond()
        assertEquals("26984457539", answer)
    }
}