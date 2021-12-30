package day


import util.InputReader
import java.lang.Integer.max
import java.lang.Integer.min

typealias VentMatrix = Array<Array<Int>>

class Day5(
    private val data: List<Pair<Pair<Int, Int>, Pair<Int, Int>>> = InputReader()
        .read("day5") { line -> parseLine(line) }
): Day {

    override fun runFirst(): String {
        // Consider for this one only horizontal or vertical lines, see assignment description
        val filteredData = data
            .filter { it.first.first == it.second.first || it.first.second == it.second.second }
        val columnSize = data.map { max(it.first.second, it.second.second) }.maxOrNull()!! + 1
        val rowSize = data.map { max(it.first.first, it.second.first) }.maxOrNull()!! + 1
        val ventMatrix: VentMatrix = Array(columnSize) { Array(rowSize) { 0 } }
        filteredData.forEach { line ->
            ventMatrix.addVent(line.first, line.second)
        }
        val amountOfMostDangerousAreas = ventMatrix.flatten().count { it >= 2 }
        return amountOfMostDangerousAreas.toString()
    }

    override fun runSecond(): String {
        val columnSize = data.map { max(it.first.second, it.second.second) }.maxOrNull()!! + 1
        val rowSize = data.map { max(it.first.first, it.second.first) }.maxOrNull()!! + 1
        val ventMatrix: VentMatrix = Array(columnSize) { Array(rowSize) { 0 } }
        data.forEach { line ->
            ventMatrix.addVent(line.first, line.second)
        }
        val amountOfMostDangerousAreas = ventMatrix.flatten().count { it >= 2 }
        return amountOfMostDangerousAreas.toString()
    }

    private fun VentMatrix.addVent(fromCoordinate: Pair<Int, Int>, toCoordinate: Pair<Int, Int>) {
        val startAtRow = fromCoordinate.second
        val startAtColumn = fromCoordinate.first
        val isHorizontal = fromCoordinate.second == toCoordinate.second
        val isVertical = fromCoordinate.first == toCoordinate.first

        when {
            isHorizontal -> {
                val row = get(startAtRow)
                val stopAtColumn = toCoordinate.first
                val from = min(stopAtColumn, startAtColumn)
                val to = max(stopAtColumn, startAtColumn)
                row.forEachIndexed { index, i ->
                    if (index in from..to) {
                        row[index] = i + 1
                    }
                }
            }
            isVertical -> {
                val stopAtRow = toCoordinate.second
                val from = min(stopAtRow, startAtRow)
                val to = max(stopAtRow, startAtRow)
                forEachIndexed { index, row ->
                    if (index in from..to) {
                        row[startAtColumn] = row[startAtColumn] + 1
                    }
                }
            }
            else -> {
                val isPositiveXDirection = fromCoordinate.first <= toCoordinate.first
                val isPositiveYDirection = fromCoordinate.second <= toCoordinate.second

                var x = fromCoordinate.first
                var y = fromCoordinate.second
                while (
                    (isPositiveXDirection && x <= toCoordinate.first)
                    || (!isPositiveXDirection && x >= toCoordinate.first)
                    || (isPositiveYDirection && y <= toCoordinate.second)
                    || (!isPositiveYDirection && y >= toCoordinate.second)
                ) {
                    this[y][x] += 1
                    x = if (isPositiveXDirection) x + 1 else x - 1
                    y = if (isPositiveYDirection) y + 1 else y - 1
                }
            }
        }
    }

    private fun VentMatrix.print() {
        forEach { row ->
            println(row.joinToString(" ") { it.toString() })
        }
    }

    companion object {

        fun parseLine(line: String): Pair<Pair<Int, Int>, Pair<Int, Int>> {
            val rawCoordinates = line.split(" -> ")
            val rawFromCoordinate = rawCoordinates.first().split(",")
            val rawToCoordinate = rawCoordinates.last().split(",")
            val fromCoordinate = rawFromCoordinate.first().toInt() to rawFromCoordinate.last().toInt()
            val toCoordinate = rawToCoordinate.first().toInt() to rawToCoordinate.last().toInt()

            return fromCoordinate to toCoordinate
        }
    }
}