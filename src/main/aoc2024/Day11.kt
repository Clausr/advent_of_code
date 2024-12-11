package aoc2024

class Day11(input: List<String>) {
    private val stoneInput = input.first().split(" ").map { it.toLong() }
    fun solvePart1(): Int {
        val blinks = 25
        var stones = stoneInput
        repeat(blinks) {
            stones = blink(stones)
        }
        return stones.size
    }

    private fun blink(stones: List<Long>): List<Long> {
        return buildList {
            for (stone in stones) {
                when {
                    stone == 0L -> {
                        add(1L)
                    }

                    stone.toString().length % 2 == 0 -> {
                        val len = stone.toString().length
                        val (first, second) = stone.toString().chunked(len / 2)

                        add(first.toLong())
                        add(second.toLong())
                    }

                    else -> {
                        add(stone * 2024L)
                    }
                }
            }
        }
    }

    fun solvePart2(): Int {
        return -1
    }
}