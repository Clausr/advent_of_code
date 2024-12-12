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

    private val cache: MutableMap<Pair<Long, Int>, Long> = mutableMapOf()


    fun solvePart2(): Long {
        // Memoized version.
        return stoneInput.sumOf { blinkMemoized(it, 75) }
    }


    private fun blinkMemoized(
        stone: Long,
        blinks: Int,
        key: Pair<Long, Int> = stone to blinks,
    ): Long = when {
        blinks == 0 -> 1
        key in cache -> cache.getValue(key)

        else -> {
            val result = when {
                stone == 0L -> blinkMemoized(1, blinks - 1)
                stone.toString().length % 2 == 0 -> {
                    val len = stone.toString().length
                    val (first, second) = stone.toString().chunked(len / 2)

                    blinkMemoized(first.toLong(), blinks - 1) + blinkMemoized(second.toLong(), blinks - 1)
                }

                else -> blinkMemoized(stone * 2024, blinks - 1)
            }
            cache[key] = result
            result
        }
    }
}
