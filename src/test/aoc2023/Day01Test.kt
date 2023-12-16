package aoc2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day01Test {
    private val exampleInput = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
""".trimIndent().split("\n")

    private val exampleInput2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day1 = Day01(exampleInput)
        assertEquals(142, day1.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day1 = Day01(resourceAsList("aoc2023/day01.txt"))
        assertEquals(55816, day1.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day1 = Day01(exampleInput2)
        assertEquals(281, day1.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day1 = Day01(resourceAsList("aoc2023/day01.txt"))
        assertEquals(54980, day1.solvePart2())
    }
}