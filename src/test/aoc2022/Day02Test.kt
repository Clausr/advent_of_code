package aoc2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day02Test {
    private val exampleInput = """A Y
B X
C Z""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day2 = Day02(exampleInput)
        assertEquals(15, day2.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day2 = Day02(resourceAsList("aoc2022/day02.txt"))
        assertEquals(14531, day2.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day2 = Day02(exampleInput)
        assertEquals(12, day2.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day2 = Day02(resourceAsList("aoc2022/day02.txt"))
        assertEquals(11258, day2.solvePart2())
    }
}
