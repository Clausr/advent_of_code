package aoc2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day11Test {
    private val exampleInput = """125 17""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day11 = Day11(exampleInput)
        assertEquals(55312, day11.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day11 = Day11(resourceAsList("aoc2024/day11.txt"))
        assertEquals(189092, day11.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day11 = Day11(exampleInput)
        assertEquals(0, day11.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day11 = Day11(resourceAsList("aoc2024/day11.txt"))
        assertEquals(0, day11.solvePart2())
    }
}