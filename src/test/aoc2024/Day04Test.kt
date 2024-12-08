package aoc2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day04Test {
    private val exampleInput = """
MMMSXXMASM
MSAMXMSMSA
AMXSXMAAMM
MSAMASMSMX
XMASAMXAMM
XXAMMXXAMA
SMSMSASXSS
SAXAMASAAA
MAMMMXMMMM
MXMXAXMASX""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day4 = Day04(exampleInput)
        assertEquals(18, day4.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day4 = Day04(resourceAsList("aoc2024/day04.txt"))
        assertEquals(2507, day4.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day4 = Day04(exampleInput)
        assertEquals(0, day4.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day4 = Day04(resourceAsList("aoc2024/day04.txt"))
        assertEquals(0, day4.solvePart2())
    }
}