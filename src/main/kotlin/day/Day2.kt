package day

import util.InputReader

class Day2(
    private val data: List<String> =  InputReader().read("day2") { it }
): Day {

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