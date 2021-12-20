package com.borek.aoc.day

import junit.framework.TestCase
import org.junit.Test

class Day7Test : TestCase() {

    @Test
    fun testRunFirstExample() {
        val day = Day7("16,1,2,0,4,2,7,1,2,14")
        val answer = day.runFirst()
        assertEquals("37", answer)
    }

    @Test
    fun testRunSecondExample() {
        val day = Day7("16,1,2,0,4,2,7,1,2,14")
        val answer = day.runSecond()
        assertEquals("168", answer)
    }
}