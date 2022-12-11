package aoc2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day1Test {
    private val exampleInput = """1000
2000
3000

4000

5000
6000

7000
8000
9000

10000""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day1 = Day1(exampleInput)
        assertEquals(24000, day1.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day1 = Day1(resourceAsList("2022/day1.txt"))
        assertEquals(70369, day1.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day1 = Day1(exampleInput)
        assertEquals(45000, day1.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day1 = Day1(resourceAsList("2022/day1.txt"))
        assertEquals(203002, day1.solvePart2())
    }
}