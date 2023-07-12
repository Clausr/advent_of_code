package aoc2022

class Day04(val input: List<String>) {
    fun solvePart1(): Int {
        val overlaps = input
            .map { it.substringBefore(",") to it.substringAfter(",") }
            .map {
                (it.first.substringBefore("-").toInt() to it.first.substringAfter("-").toInt()) to (it.second.substringBefore("-")
                    .toInt() to it.second.substringAfter("-").toInt())
            }
            .count {
                it.first.first <= it.second.first && it.first.second >= it.second.second
                    || it.second.first <= it.first.first && it.second.second >= it.first.second
            }

        return overlaps
    }

    fun solvePart2(): Int {
        val anyOverlaps = input
            .map { it.substringBefore(",") to it.substringAfter(",") }
            .map {
                (it.first.substringBefore("-").toInt() to it.first.substringAfter("-").toInt()) to (it.second.substringBefore("-")
                    .toInt() to it.second.substringAfter("-").toInt())
            }
            .map { (it.first.first..it.first.second) to (it.second.first..it.second.second) }
            .count {
                it.first.intersect(it.second).isNotEmpty()
            }

        return anyOverlaps
    }
}
