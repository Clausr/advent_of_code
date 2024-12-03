package aoc2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day02Test {
    private val exampleInput = """7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day2 = Day02(exampleInput)
        assertEquals(2, day2.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day2 = Day02(resourceAsList("aoc2024/day02.txt"))
        assertEquals(356, day2.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day2 = Day02(exampleInput)
        assertEquals(4, day2.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day2 = Day02(resourceAsList("aoc2024/day02.txt"))
        assertEquals(413, day2.solvePart2())
    }
}