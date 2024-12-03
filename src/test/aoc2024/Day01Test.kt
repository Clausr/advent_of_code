package aoc2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day01Test {
    private val exampleInput = """3   4
4   3
2   5
1   3
3   9
3   3""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day1 = Day01(exampleInput)
        assertEquals(11, day1.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day1 = Day01(resourceAsList("aoc2024/day01.txt"))
        assertEquals(1319616, day1.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day1 = Day01(exampleInput)
        assertEquals(31, day1.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day1 = Day01(resourceAsList("aoc2024/day01.txt"))
        assertEquals(27267728, day1.solvePart2())
    }
}