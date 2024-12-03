package aoc2024

class Day01(input: List<String>) {
    private val locationIds1 = input.map {
        val splitString = it.split("   ")
        splitString.first().toInt()
    }

    private val locationIds2 = input.map {
        val splitString = it.split("   ")
        splitString.last().toInt()
    }

    fun solvePart1(): Int {
        val left = locationIds1.sorted()
        val right = locationIds2.sorted()

        var sumOfDistance = 0
        left.forEachIndexed { index, leftValue ->
            val rightValue = right[index]

            val distance = if (leftValue > rightValue) {
                leftValue - rightValue
            } else {
                rightValue - leftValue
            }

            sumOfDistance += distance
        }

        return sumOfDistance
    }

    fun solvePart2(): Int {
        return locationIds1.sumOf { leftValue ->
            locationIds2.count { it == leftValue } * leftValue
        }
    }
}