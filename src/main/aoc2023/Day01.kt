package aoc2023

class Day01(val input: List<String>) {
    fun solvePart1(): Int {
        val sum = input.sumOf {
            addFirstAndLastDigit(it)
        }
        return sum
    }

    private fun addFirstAndLastDigit(line: String): Int {
        val first = line.firstOrNull { it.isDigit() } //?.digitToInt() ?: 0
        val last = line.findLast { it.isDigit() } //?.digitToInt() ?: 0
        println("First digit: $first $last")
        return "$first$last".toInt()
    }

    private fun convertToDigits(line: String): String {
        val numberWords = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

        var newLine = line



        numberWords.forEachIndexed { i, numberWord ->
            val indexOfMatch = line.indexOf(numberWord)
            if (indexOfMatch != -1) {
                println("Index of $numberWord $indexOfMatch")
            }
            newLine = newLine.replace(numberWord, (i + 1).toString())
        }
        println("New line: $newLine")
        return newLine
    }

    fun solvePart2(): Int {
        val sum = input.sumOf {
            val convertedString = convertToDigits(it)
            addFirstAndLastDigit(convertedString)
        }
        return sum
    }
}