package aoc2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day2Test {
    private val exampleInput = """A Y
B X
C Z""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day2 = Day2(exampleInput)
        assertEquals(15, day2.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day2 = Day2(resourceAsList("2022/day2.txt"))
        assertEquals(14531, day2.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day2 = Day2(exampleInput)
        assertEquals(12, day2.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day2 = Day2(resourceAsList("2022/day2.txt"))
        assertEquals(11258, day2.solvePart2())
    }
}