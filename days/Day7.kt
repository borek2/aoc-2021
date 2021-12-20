package com.borek.aoc.day

import android.content.Context
import com.borek.aoc.util.InputReader
import kotlin.math.abs

class Day7(): Day<List<Int>> {

    override lateinit var data: List<Int>

    constructor(context: Context) : this() {
        data = InputReader()
            .read(context.assets, "day7") { line ->
                line.split(",").map { it.toInt() }
            }
            .flatten()
    }

    constructor(data: String) : this() {
        this.data = data.split(",").map { it.toInt() }
    }

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