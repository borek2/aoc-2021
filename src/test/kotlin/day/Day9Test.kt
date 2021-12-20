package day

import junit.framework.TestCase
import org.junit.Test

class Day9Test : TestCase() {

    @Test
    fun testRunFirstExample() {
        val day = Day9(
            listOf(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
            ).map { it.map { it.digitToInt() } }
        )
        val result = day.runFirst()
        assertEquals("15", result)
    }

    @Test
    fun testRunSecondExample() {
        val day = Day9(
            listOf(
                "2199943210",
                "3987894921",
                "9856789892",
                "8767896789",
                "9899965678"
            ).map { it.map { it.digitToInt() } }
        )
        val result = day.runSecond()
        assertEquals("1134", result)
    }
}