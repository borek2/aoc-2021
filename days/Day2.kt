package com.borek.aoc.day

import android.content.Context
import android.util.Log
import com.borek.aoc.util.InputReader

class Day2(context: Context): Day<List<String>> {

    override val data by lazy {
        InputReader().read(context.assets, "day2") { it }
    }

    override fun runFirst(): String {
        val (horizontal, depth) = data
            .fold(0 to 0) { acc, s ->
                val splitted = s.split(" ")
                val direction = splitted.first()
                val amount = splitted.last().toInt()
                when (direction) {
                    "forward" -> (acc.first + amount) to acc.second
                    "down" -> acc.first to (acc.second + amount)
                    "up" -> acc.first to (acc.second - amount)
                    else -> throw Exception()
                }
            }
        val multiplied = horizontal * depth

        return multiplied.toString()
    }

    override fun runSecond(): String {
        var aim = 0
        val (horizontal, depth) = data
            .fold(0 to 0) { acc, s ->
                val splitted = s.split(" ")
                val direction = splitted.first()
                val amount = splitted.last().toInt()

                when (direction) {
                    "forward" -> (acc.first + amount) to (acc.second + (aim * amount))
                    "down" -> {
                        aim += amount
                        acc
                    }
                    "up" -> {
                        aim -= amount
                        acc
                    }
                    else -> throw Exception()
                }
            }
        val multiplied = horizontal * depth

        return multiplied.toString()
    }
}