package day

import util.InputReader
import kotlin.math.pow

class Day3(
    private val data: List<String> = InputReader().read("day3") { it }
): Day {

    override fun runFirst(): String {
        val (gamma, epsilon) = data.calculateGammaEpsilon()
        val gammaDecimal = gamma.toDecimal()
        val epsilonDecimal = epsilon.toDecimal()

        return (gammaDecimal * epsilonDecimal).toString()
    }

    override fun runSecond(): String {
        var oxygenGeneratorRatingList = data
        var index = 0
        while (oxygenGeneratorRatingList.count() > 1) {
            val (gamma, _) = oxygenGeneratorRatingList.calculateGammaEpsilon()
            val criteria = gamma[index]
            oxygenGeneratorRatingList = oxygenGeneratorRatingList
                .filter { it[index] == criteria }
                .takeIf { it.count() > 0 }
                ?: oxygenGeneratorRatingList
            index++
        }

        var co2ScrubberRatingList = data
        index = 0
        while (co2ScrubberRatingList.count() > 1) {
            val (_, epsilon) = co2ScrubberRatingList.calculateGammaEpsilon()
            val criteria = epsilon[index]
            co2ScrubberRatingList = co2ScrubberRatingList
                .filter { it[index] == criteria }
                .takeIf { it.count() > 0 }
                ?: co2ScrubberRatingList
            index++
        }

        val oxygenGeneratorRatingDecimal = oxygenGeneratorRatingList.first().toDecimal()
        val co2ScrubberRatingDecimal = co2ScrubberRatingList.first().toDecimal()

        return (oxygenGeneratorRatingDecimal * co2ScrubberRatingDecimal).toString()
    }

    private fun List<String>.calculateGammaEpsilon(): Pair<String, String> {
        val numberOfBits = first().count()
        val totalAmount = count()
        val amountOfOnes = Array(numberOfBits) { 0 }

        forEach { bitString ->
            bitString.forEachIndexed { index, bit ->
                amountOfOnes[index] += bit.digitToInt()
            }
        }
        return amountOfOnes.fold("" to "") { acc, amount ->
            if (amount >= (totalAmount - amount)) {
                acc.first.plus("1") to acc.second.plus("0")
            } else {
                acc.first.plus("0") to acc.second.plus("1")
            }
        }
    }

    private fun String.toDecimal(): Int {
        return reversed().foldIndexed(0) { index, acc, v ->
            acc + (v.digitToInt() * 2.0.pow(index.toDouble()).toInt())
        }
    }
}