package aoc2022

import Day08
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day08Test {
    private val exampleInput = """30373
25512
65332
33549
35390""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day8 = Day08(exampleInput)
        assertEquals(21, day8.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day8 = Day08(resourceAsList("aoc2022/day08.txt"))
        assertEquals(1827, day8.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day8 = Day08(exampleInput)
        assertEquals(8, day8.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day8 = Day08(resourceAsList("aoc2022/day08.txt"))
        assertEquals(335580, day8.solvePart2())
    }
}
