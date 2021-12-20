package com.borek.aoc.day

import android.content.Context
import com.borek.aoc.util.InputReader

class Day8() : Day<List<Pair<List<String>, List<String>>>> {

    override lateinit var data: List<Pair<List<String>, List<String>>>

    constructor(context: Context) : this() {
        data = InputReader()
            .read(context.assets, "day8") { line -> readLine(line) }
    }

    constructor(data: List<String>) : this() {
        this.data = data.map { readLine(it) }
    }

    override fun runFirst(): String {
        val amountOfUniqueDigits = data
            .map { it.second }
            .sumOf {
                it.count { string ->
                    when (string.length) {
                        ONE, FOUR, SEVEN, EIGHT -> true
                        else -> false
                    }
                }
            }

        return amountOfUniqueDigits.toString()
    }

    override fun runSecond(): String {
        val summed = data.sumOf { (signals, output) ->
            // Step 1
            val one = signals.first { it.length == ONE }
            val four = signals.first { it.length == FOUR }
            val seven = signals.first { it.length == SEVEN }
            val eight = signals.first { it.length == EIGHT }
            // 0 6 9
            var zero: String? = null
            var six: String? = null
            var nine: String? = null
            val possible069Signals = signals.filter { it.length == 6 }
            val possible0Signals = possible069Signals.filter { signal ->
                signal.toList().containsAll(one.toList())
                        && signal.toList().containsAll(seven.toList())
                        && !signal.toList().containsAll(eight.toList())
                        && !signal.toList().containsAll(four.toList())
            }
            for (possible0Signal in possible0Signals) {
                val possible6Signals = possible069Signals.minus(possible0Signal).filter { signal ->
                    !signal.toList().containsAll(one.toList())
                            && !signal.toList().containsAll(seven.toList())
                            && !signal.toList().containsAll(eight.toList())
                            && !signal.toList().containsAll(four.toList())
                            && !signal.toList().containsAll(possible0Signal.toList())
                }
                for (possible6Signal in possible6Signals) {
                    val possibleNineSignals = possible069Signals.minus(possible0Signal).minus(possible6Signal).filter { signal ->
                        signal.toList().containsAll(one.toList())
                                && signal.toList().containsAll(seven.toList())
                                && !signal.toList().containsAll(eight.toList())
                                && signal.toList().containsAll(four.toList())
                                && !signal.toList().containsAll(possible0Signal.toList())
                                && !signal.toList().containsAll(possible6Signal.toList())
                    }
                    if (possibleNineSignals.count() == 1) {
                        zero = possible0Signal.takeIf { zero == null } ?: zero
                        nine = possibleNineSignals.first().takeIf { nine == null } ?: nine
                        six = possible6Signal.takeIf { six == null } ?: six
                    }
                }
            }
            // 2, 3, 5
            var two: String? = null
            var three: String? = null
            var five: String? = null
            val possible235Signals = signals.filter { it.length == 5 }
            val possible2Signals = possible235Signals.filter { signal ->
                !signal.toList().containsAll(one.toList())
                        && !signal.toList().containsAll(seven.toList())
                        && !signal.toList().containsAll(eight.toList())
                        && !signal.toList().containsAll(four.toList())
            }
            for (possible2Signal in possible2Signals) {
                val possible3Signals = possible235Signals.minus(possible2Signal).filter { signal ->
                    signal.toList().containsAll(one.toList())
                            && signal.toList().containsAll(seven.toList())
                            && !signal.toList().containsAll(eight.toList())
                            && !signal.toList().containsAll(four.toList())
                            && !signal.toList().containsAll(possible2Signal.toList())
                }
                for (possible3Signal in possible3Signals) {
                    val possible5Signals = possible235Signals.minus(possible2Signal).minus(possible3Signal).filter { signal ->
                        !signal.toList().containsAll(one.toList())
                                && !signal.toList().containsAll(seven.toList())
                                && !signal.toList().containsAll(eight.toList())
                                && !signal.toList().containsAll(four.toList())
                                && !signal.toList().containsAll(possible2Signal.toList())
                                && !signal.toList().containsAll(possible3Signal.toList())
                                && nine!!.toList().containsAll(signal.toList())
                    }
                    if (possible5Signals.count() == 1) {
                        two = possible2Signal.takeIf { two == null } ?: two
                        three = possible3Signal.takeIf { three == null } ?: three
                        five = possible5Signals.first().takeIf { five == null } ?: five
                    }
                }
            }

            // Step 2
            val values = mutableListOf<Int>()
            for (o in output) {
                val isOne = o.length == ONE
                val isFour = o.length == FOUR
                val isSeven = o.length == SEVEN
                val isEight = o.length == EIGHT
                val isZero = o.length == 6 && o.toSet() == zero!!.toSet()
                val isSix = o.length == 6 && o.toSet() == six!!.toSet()
                val isNine = o.length == 6 && o.toSet() == nine!!.toSet()
                val isTwo = o.length == 5 && o.toSet() == two!!.toSet()
                val isThree = o.length == 5 && o.toSet() == three!!.toSet()
                val isFive = o.length == 5 && o.toSet() == five!!.toSet()
                when {
                    isOne -> values.add(1)
                    isFour -> values.add(4)
                    isSeven -> values.add(7)
                    isEight -> values.add(8)
                    isZero -> values.add(0)
                    isNine -> values.add(9)
                    isTwo -> values.add(2)
                    isSix -> values.add(6)
                    isThree -> values.add(3)
                    isFive -> values.add(5)
                }
            }

            values.joinToString("") { it.toString() }.toInt()
        }

        return summed.toString()
    }

    companion object {

        private const val ONE = 2
        private const val FOUR = 4
        private const val SEVEN = 3
        private const val EIGHT = 7

        private fun readLine(line: String): Pair<List<String>, List<String>> {
            val splitted = line.split(" | ")
            val signalPatterns = splitted.first().split(" ")
            val outPutValue = splitted.last().split(" ")

            return signalPatterns to outPutValue
        }
    }
}