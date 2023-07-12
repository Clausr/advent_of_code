package aoc2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day04Test {
    private val exampleInput = """2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day4 = Day04(exampleInput)
        assertEquals(2, day4.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day4 = Day04(resourceAsList("aoc2022/day04.txt"))
        assertEquals(524, day4.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day4 = Day04(exampleInput)
        assertEquals(4, day4.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day4 = Day04(resourceAsList("aoc2022/day04.txt"))
        assertEquals(798, day4.solvePart2())
    }
}
