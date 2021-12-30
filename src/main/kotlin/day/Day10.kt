package day

import util.InputReader

class Day10(
    private val data: List<String> = InputReader().read("day10") { it }
): Day {

    override fun runFirst(): String {
        return data
            .sumOf { findFirstIncorrectChunk(it.toList(), emptyList())?.score ?: 0 }
            .toString()
    }

    override fun runSecond(): String {
        return "0"
    }

    private fun findFirstIncorrectChunk(characters: List<Char>, toBeClosed: List<Chunk>): Chunk? {
        if (characters.isEmpty()) {
            return null
        }

        val checkCharacter = characters.first()
        val chunkContainingStart = Chunk.values().firstOrNull { it.start == checkCharacter }
        val isOpening = chunkContainingStart != null
        val otherCharacters = characters.takeLast(characters.size - 1)

        return if (isOpening) {
            findFirstIncorrectChunk(otherCharacters, toBeClosed.plus(chunkContainingStart!!))
        } else {
            val chunkContainingStop = Chunk.values().firstOrNull { it.stop == checkCharacter }
            val isValid = toBeClosed.isNotEmpty() && chunkContainingStop!! == toBeClosed.last()
            val otherToBeClosed = toBeClosed.take(toBeClosed.size - 1)

            if (isValid) {
                findFirstIncorrectChunk(otherCharacters, otherToBeClosed)
            } else {
                chunkContainingStop
            }
        }
    }

    enum class Chunk(val start: Char, val stop: Char, val score: Int) {
        CHUNK_1('(', ')', 3),
        CHUNK_2('[', ']', 57),
        CHUNK_3('{', '}', 1197),
        CHUNK_4('<', '>', 25137),
    }

    companion object {

        /**
         * ): 3 points.
        ]: 57 points.
        }: 1197 points.
        >: 25137 points.
         */

    }
}