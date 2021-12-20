package com.borek.aoc.day

import junit.framework.TestCase
import org.junit.Test

class Day3Test : TestCase() {

    @Test
    fun testRunFirstExample() {
        val day = Day3(listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010",
        ))
        val answer = day.runFirst()
        assertEquals("198", answer)
    }


    @Test
    fun testRunSecondExample() {
        val day = Day3(listOf(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010",
        ))
        val answer = day.runSecond()
        assertEquals("230", answer)
    }
}