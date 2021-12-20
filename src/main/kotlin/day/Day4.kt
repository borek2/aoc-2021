package day


import util.InputReader
import java.lang.NumberFormatException

class Day4(
    private val data: Bingo = InputReader()
        .read("day4") { it }
        .let { Bingo(it) }
): Day<Day4.Bingo> {

    override fun runFirst(): String {
        return data.getScore()?.toString() ?: "-"
    }

    override fun runSecond(): String {
        return data.getLastScore()?.toString() ?: "-"
    }

    class Bingo(data: List<String>) {

        private val cards: List<Card> = data
            .takeLast(data.count() - 1)
            .chunked(6) { Card(it) }

        private val order: List<Int> = data
            .first()
            .split(",")
            .map { it.toInt() }

        fun getScore(): Int? {
            var winningCard: Card? = null
            var index = 0
            while (winningCard == null && index < order.size) {
                winningCard = cards.firstOrNull { card ->
                    card.mark(order[index])
                    card.hasBingo()
                }
                index++
            }

            return winningCard?.score
        }

        fun getLastScore(): Int? {
            val winningCards: MutableList<Card> = mutableListOf()
            var index = 0
            while (winningCards.size < cards.size && index < order.size) {
                cards
                    .minus(winningCards)
                    .filter { card ->
                        card.mark(order[index])
                        card.hasBingo()
                    }
                    .let { winningCards.addAll(it) }
                index++
            }
            return winningCards.lastOrNull()?.score
        }

        class Card(data: List<String>) {

            private val marked: MutableList<Int> = mutableListOf()

            private val entries: List<List<Int>> = data
                .filter { it.isNotEmpty() }
                .map { row ->
                    row.split(" ").mapNotNull {
                        try {
                            it.toInt()
                        } catch (e: NumberFormatException) {
                            null
                        }
                    }
                }

            private val unmarkedSummed: Int
                get() {
                    return entries
                        .flatten()
                        .filter { !marked.contains(it) }
                        .sum()
                }

            val score: Int
                get() {
                    return unmarkedSummed * marked.last()
                }

            fun mark(entry: Int) {
                if (entries.flatten().contains(entry)) {
                    marked.add(marked.count(), entry)
                }
            }

            fun hasBingo(): Boolean {
                val rows = entries
                    .filter { it.all { marked.contains(it) } }
                val columnAmount = entries.first().count()
                val columns = (0 until columnAmount)
                    .map { c -> entries.map { it[c] } }
                    .filter { it.all { marked.contains(it) } }

                return (rows.firstOrNull() ?: columns.firstOrNull()) != null
            }
        }
    }
}