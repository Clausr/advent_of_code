package aoc2023

class Day03(val input: List<String>) {

    data class NumberThing(val number: Int, val range: IntRange)

    private fun findNumberRangesOnRow(input: String): List<NumberThing> {
        val numbers = mutableListOf<NumberThing>()

        for (i in input.indices) {

            val charAtIndex = input.getOrNull(i)
            if (charAtIndex != null && charAtIndex.isDigit()) {
                var number = charAtIndex.toString()

                var j = i + 1
                while (j < input.length && input[j].isDigit()) {
                    number += input[j]
                    j++
                }

                numbers.add(NumberThing(number.toInt(), range = i..j))
            }
        }

        return numbers
    }

    fun solvePart1(): Int {
        val numbers = input.map {
            findNumberRangesOnRow(it).count()
        }
        return numbers.count()
    }

    fun solvePart2(): Int {
        return -1
    }
}