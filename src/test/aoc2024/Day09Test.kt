package aoc2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day09Test {
    private val exampleInput = """2333133121414131402""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day9 = Day09(exampleInput)
        assertEquals(1928L, day9.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day9 = Day09(resourceAsList("aoc2024/day09.txt"))
        assertEquals(6225730762521L, day9.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day9 = Day09(exampleInput)
        assertEquals(2858L, day9.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day9 = Day09(resourceAsList("aoc2024/day09.txt"))
        assertEquals(0, day9.solvePart2())
    }
}