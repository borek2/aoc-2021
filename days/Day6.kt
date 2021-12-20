package com.borek.aoc.day

import android.content.Context
import com.borek.aoc.util.InputReader
import java.math.BigInteger

class Day6(): Day<List<Int>> {

    override lateinit var data: List<Int>

    constructor(context: Context) : this() {
        data = InputReader()
            .read(context.assets, "day6") { line ->
                line.split(",").map { it.toInt() }
            }
            .flatten()
    }

    constructor(data: String) : this() {
        this.data = data.split(",").map { it.toInt() }
    }

    override fun runFirst(): String {
        val targetDay = 80
        var fish = data.toMutableList()
        for (i in 0 until targetDay) {
            val copied = MutableList(fish.size) { fish[it] }
            for ((index, f) in fish.withIndex()) {
                if (f == 0) {
                    copied.add(SPAWN_RATE_DAYS_NEW_FISH)
                    copied[index] = SPAWN_RATE_DAYS
                } else {
                    copied[index] = fish[index] - 1
                }
            }
            fish = copied
        }

        return fish.count().toString()
    }

    override fun runSecond(): String {
        val targetDay = 256
        val fish = Array(SPAWN_RATE_DAYS_NEW_FISH + 1) { BigInteger.ZERO }
        data.forEach {
            fish[it] = fish[it] + BigInteger.ONE
        }
        for (i in 0 until targetDay) {
            var newFish = BigInteger.ZERO
            var revivedFish = BigInteger.ZERO
            for ((index, f) in fish.withIndex()) {
                when (index) {
                    0 -> {
                        newFish = f
                        revivedFish = f
                        fish[index] = BigInteger.ZERO
                    }
                    SPAWN_RATE_DAYS -> {
                        fish[index - 1] = fish[index]
                        fish[index] = revivedFish
                    }
                    SPAWN_RATE_DAYS_NEW_FISH -> {
                        fish[index - 1] = fish[index]
                        fish[index] = newFish
                    }
                    else -> {
                        fish[index - 1] += fish[index]
                        fish[index] = BigInteger.ZERO
                    }
                }
            }
        }
        return fish.sumOf { it }.toString()
    }

    companion object {

        private const val SPAWN_RATE_DAYS = 6
        private const val SPAWN_RATE_DAYS_NEW_FISH = 8
    }
}