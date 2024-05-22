package aoc2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day03Test {
    private val exampleInput = """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...${'$'}.*....
.664.598..""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day3 = Day03(exampleInput)
        assertEquals(0, day3.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day3 = Day03(resourceAsList("aoc2023/day03.txt"))
        assertEquals(0, day3.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day3 = Day03(exampleInput)
        assertEquals(0, day3.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day3 = Day03(resourceAsList("aoc2023/day03.txt"))
        assertEquals(0, day3.solvePart2())
    }
}