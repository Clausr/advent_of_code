package aoc2023

class Day01(val input: List<String>) {
    fun solvePart1(): Int {
        val sum = input.sumOf {
            addFirstAndLastDigit(it)
        }
        return sum
    }

    private fun addFirstAndLastDigit(line: String): Int {
        val first = line.first { it.isDigit() }
        val last = line.last { it.isDigit() }
        return "$first$last".toInt()
    }

    val words = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    private fun String.possibleWordsAt(startingAt: Int): List<String> =
        (3..5).map { len ->
            substring(startingAt, (startingAt + len).coerceAtMost(length))
        }

    fun solvePart2(): Int {
        val sum = input.sumOf {
            addFirstAndLastDigit(
                it.mapIndexedNotNull { index, c ->
                    if (c.isDigit()) c
                    else
                        it.possibleWordsAt(index).firstNotNullOfOrNull {candidate ->
                            words[candidate]
                        }
                }.joinToString()
            )
        }
        return sum
    }
}