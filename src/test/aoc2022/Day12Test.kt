package aoc2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day12Test {
    private val exampleInput = """Sabqponm
abcryxxl
acigcszExk
acctuvwj
abdefghi""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day12 = Day12(exampleInput)
        assertEquals(31, day12.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day12 = Day12(resourceAsList("2022/day12.txt"))
        assertEquals(383, day12.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day12 = Day12(exampleInput)
        assertEquals(29, day12.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day12 = Day12(resourceAsList("2022/day12.txt"))
        assertEquals(377, day12.solvePart2())
    }
}