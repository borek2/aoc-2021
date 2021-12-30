package day

import util.InputReader
import java.math.BigInteger
import kotlin.math.floor

class Day10(
    private val data: List<String> = InputReader().read("day10") { it }
): Day {

    override fun runFirst(): String {
        return data
            .sumOf { findFirstIncorrectChunk(it.toList(), emptyList())?.score ?: 0 }
            .toString()
    }

    override fun runSecond(): String {
        return data
            .filter { findFirstIncorrectChunk(it.toList(), emptyList()) == null }
            .map { findChunksThatNeedClosing(it.toList(), emptyList()).calculateScore() }
            .sorted()
            .let { scores -> scores[floor(scores.size / 2.0).toInt()] }
            .toString()
    }

    /**
     * The score is determined by considering the completion string character-by-character.
     * Start with a total score of 0. Then, for each character, multiply the total score by 5 and then increase the
     * total score by the point value given for the character.
     */
    private fun List<Chunk>.calculateScore(): BigInteger {
        return fold(BigInteger.ZERO) { acc, chunk ->
            (acc * BigInteger.valueOf(5L)) + BigInteger.valueOf(chunk.autoCompleteScore.toLong())
        }
    }

    private fun findChunksThatNeedClosing(characters: List<Char>, toBeClosed: List<Chunk>): List<Chunk> {
        // Assert that line is incomplete, not incorrect
        if (characters.isEmpty()) {
            return toBeClosed.reversed()
        }

        val checkCharacter = characters.first()
        val chunkContainingStart = Chunk.values().firstOrNull { it.start == checkCharacter }
        val isOpening = chunkContainingStart != null
        val otherCharacters = characters.takeLast(characters.size - 1)

        return if (isOpening) {
            findChunksThatNeedClosing(otherCharacters, toBeClosed.plus(chunkContainingStart!!))
        } else {
            val otherToBeClosed = toBeClosed.take(toBeClosed.size - 1)

            findChunksThatNeedClosing(otherCharacters, otherToBeClosed)
        }
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

    enum class Chunk(val start: Char, val stop: Char, val score: Int, val autoCompleteScore: Int) {
        CHUNK_1('(', ')', 3, 1),
        CHUNK_2('[', ']', 57, 2),
        CHUNK_3('{', '}', 1197, 3),
        CHUNK_4('<', '>', 25137, 4),
    }
}