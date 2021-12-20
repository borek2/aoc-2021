package day

import util.InputReader
import kotlin.math.abs

class Day7(
    private val data: List<Int> = InputReader()
        .read("day7") { line ->
            line.split(",").map { it.toInt() }
        }
        .flatten()
): Day<List<Int>> {

    constructor(data: String) : this(data.split(",").map { it.toInt() })

    override fun runFirst(): String {
        val lowest = data.minByOrNull { it }!!
        val highest = data.maxByOrNull { it }!!
        val alignment = (lowest..highest).minByOrNull { data.calculateFuelCosts(it) }!!

        return data.calculateFuelCosts(alignment).toString()
    }

    override fun runSecond(): String {
        val lowest = data.minByOrNull { it }!!
        val highest = data.maxByOrNull { it }!!
        val alignment = (lowest..highest).minByOrNull { data.calculateExpensiveFuelCosts(it) }!!

        return data.calculateExpensiveFuelCosts(alignment).toString()
    }

    private fun List<Int>.calculateFuelCosts(horizontalAlignment: Int): Int {
        return fold(0) { acc, i ->
            acc + abs(i - horizontalAlignment)
        }
    }

    private fun List<Int>.calculateExpensiveFuelCosts(horizontalAlignment: Int): Int {
        return fold(0) { acc, i ->
            var alignmentLeft = abs(i - horizontalAlignment)
            var multiplier = 1
            var fuel = 0
            while (alignmentLeft != 0) {
                fuel += multiplier
                multiplier += 1
                alignmentLeft -= 1
            }
            acc + fuel
        }
    }
}