package day

import util.InputReader


class Day9(
    private val data: List<List<Int>> = InputReader()
        .read("day9") { line ->
            line.map { it.digitToInt() }
        }
): Day<List<List<Int>>> {

    override fun runFirst(): String {
        val lowPoints = mutableListOf<Int>()
        for ((y, row) in data.withIndex()) {
            for ((x, height) in row.withIndex()) {
                if (isLowPoint(x, y)) {
                    lowPoints.add(height)
                }
            }
        }
        return lowPoints.sumOf { 1 + it }.toString()
    }

    override fun runSecond(): String {
        val topBasins = mutableListOf<List<Pair<Int, Int>>>()
        for ((y, row) in data.withIndex()) {
            for ((x, _) in row.withIndex()) {
                if (isLowPoint(x, y)) {
                    val basinPoints = crawlFrom(x, y, mutableListOf())
                    topBasins.add(basinPoints)
                }
            }
        }
        val sorted = topBasins.sortedByDescending { it.count() }
        return (sorted[0].count() * sorted[1].count() * sorted[2].count()).toString()
    }

    private fun crawlFrom(x: Int, y: Int, basinPoints: MutableList<Pair<Int, Int>>): List<Pair<Int, Int>> {
        val height = data.getOrNull(y)?.getOrNull(x)
        if (height == null || height == 9 || basinPoints.contains(x to y)) {
            return emptyList()
        }
        basinPoints.add(x to y)
        basinPoints.addAll(crawlFrom(x - 1, y, basinPoints))
        basinPoints.addAll(crawlFrom(x, y - 1, basinPoints))
        basinPoints.addAll(crawlFrom(x, y + 1, basinPoints))
        basinPoints.addAll(crawlFrom(x + 1, y, basinPoints))

        return basinPoints.toSet().toList()
    }

    private fun isLowPoint(x: Int, y: Int): Boolean {
        var isLowPoint = true
        val height = data[y][x]
        for (addX in listOf(-1, 1)) {
            val adjacent = data.getOrNull(y)?.getOrNull(x + addX) ?: 10
            val isHigher = adjacent > height
            if (!isHigher) {
                isLowPoint = false
            }
        }
        for (addY in listOf(-1, 1)) {
            val adjacent = data.getOrNull(y + addY)?.getOrNull(x) ?: 10
            val isHigher = adjacent > height
            if (!isHigher) {
                isLowPoint = false
            }
        }

        return isLowPoint
    }

    enum class Direction {
        TOP, BOTTOM, LEFT, RIGHT
    }
}