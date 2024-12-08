package aoc2024

class Day04(private val input: List<String>) {

    private fun rotateGrid(input: List<String>): List<String> {
        val rotatedMap = mutableMapOf<Int, String>()
        input.forEach { y ->
            y.reversed().forEachIndexed { xIndex, x ->
                rotatedMap[xIndex] = rotatedMap[xIndex].orEmpty() + x.toString()
            }
        }

        return rotatedMap.values.toList()
    }

    /**
     * M M M S
     * M S A M
     * A M X S
     * M S A M
     * Since we can rotate the grid, we just need to parse this horizontally and diagonal in a single direction
     */
    private fun searchForWord(input: List<String>): Int {
        // Go through horizontally:
        val horizontalCount = input.sumOf { countXmas(it) }

        val listHeight = input.size - 1
        var diagonalCount = 0

        // Logic for 0 -> Down
        input.forEachIndexed { index, line ->
            var internalY = index
            var temp = ""
            for (x in 0..listHeight - index) {
                temp += input[internalY][x]
                internalY += 1
            }
            diagonalCount += countXmas(temp)
        }

        // Logic along x axis
        for (x in 1..<input.first().length - 1) {
            var internalX = x
            var temp = ""
            for (y in 0..input.first().length - 1 - x) {
                temp += input[y][internalX]
                internalX += 1
            }
            diagonalCount += countXmas(temp)
        }

        return horizontalCount + diagonalCount
    }

    private fun countXmas(input: String): Int {
        val xmasRegex = "XMAS".toRegex()
        val finds = xmasRegex.findAll(input).count()
        return finds
    }

    fun solvePart1(): Int {
        var localGrid = input

        var sum = 0
        for (rotation in 1..4) {
            localGrid = rotateGrid(localGrid)

            val wordSearchRes = searchForWord(localGrid)

            sum += wordSearchRes
        }

        return sum
    }

    private fun findMas(input: List<String>): Int {

        var sum = 0
        input.mapIndexed { yIndex, xLine ->
            if (yIndex + 3 <= input.size) {
                xLine.indices.mapNotNull { xIndex ->
                    if (xIndex + 3 <= xLine.length) {
                        var tempY = yIndex
                        var tempX = xIndex
                        var temp = ""
                        for (x in xIndex..xIndex + 2) {
                            temp += input[tempY][x]
                            tempY += 1
                            tempX += 1
                        }

                        var tempYRev = yIndex

                        var tempBackwards = ""
                        for (xRev in xIndex + 2 downTo xIndex) {
                            tempBackwards += input[tempYRev][xRev]
                            tempYRev += 1
                        }

                        if (temp == "MAS" && tempBackwards == "MAS") {
                            sum += 1
                        }
                    }
                    sum
                }
            }
        }

        return sum
    }

    fun solvePart2(): Int {
        var localGrid = input

        var sum = 0
        for (rotation in 1..4) {
            localGrid = rotateGrid(localGrid)

            val wordSearchRes = findMas(localGrid)

            sum += wordSearchRes
        }

        return sum
    }
}