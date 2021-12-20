package com.borek.aoc.day

import junit.framework.TestCase
import org.junit.Test

class Day5Test : TestCase() {

    @Test
    fun testRunFirstExample() {
        val day = Day5(
            listOf(
                "0,9 -> 5,9",
                "8,0 -> 0,8",
                "9,4 -> 3,4",
                "2,2 -> 2,1",
                "7,0 -> 7,4",
                "6,4 -> 2,0",
                "0,9 -> 2,9",
                "3,4 -> 1,4",
                "0,0 -> 8,8",
                "5,5 -> 8,2",
            )
        )
        val answer = day.runFirst()
        assertEquals("5", answer)
    }

    @Test
    fun testRunSecondExample() {
        val day = Day5(
            listOf(
                "0,9 -> 5,9",
                "8,0 -> 0,8",
                "9,4 -> 3,4",
                "2,2 -> 2,1",
                "7,0 -> 7,4",
                "6,4 -> 2,0",
                "0,9 -> 2,9",
                "3,4 -> 1,4",
                "0,0 -> 8,8",
                "5,5 -> 8,2",
            )
        )
        val answer = day.runSecond()
        assertEquals("12", answer)
    }
}