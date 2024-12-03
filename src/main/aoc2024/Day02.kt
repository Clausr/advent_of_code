package aoc2024

class Day02(reports: List<String>) {
    private val levelReport = reports.map { report ->
        report.split(" ")
    }.map { levels ->
        levels.map { level -> level.toInt() }
    }

    /**
     * The levels are either all increasing or all decreasing.
     * Any two adjacent levels differ by at least one and at most three.
     */
    private fun isSafe(levels: List<Int>): Boolean {
        val differences = levels.zipWithNext { a, b ->
            a - b
        }
        return differences.all { it in -3..3 } && (differences.all { it > 0 } || differences.all { it < 0 })
    }

    fun solvePart1(): Int = levelReport.count(::isSafe)

    fun solvePart2(): Int = levelReport.count { levels ->
        levels.indices.any {
            val skipped = levels.toMutableList().apply { removeAt(it) }
            isSafe(skipped)
        }
    }
}