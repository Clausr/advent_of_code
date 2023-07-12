package aoc2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day06Test {
    private val exampleInput = """mjqjpqmgbljsphdztnvjfqwrcgsmlb""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day6 = Day06(exampleInput)
        assertEquals(7, day6.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day6 = Day06(resourceAsList("aoc2022/day06.txt"))
        assertEquals(1802, day6.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day6 = Day06(exampleInput)
        assertEquals(19, day6.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day6 = Day06(resourceAsList("aoc2022/day06.txt"))
        assertEquals(3551, day6.solvePart2())
    }
}
