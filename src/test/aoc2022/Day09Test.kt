package aoc2022

import Day09
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day09Test {
    private val exampleInput = """R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2""".trimIndent().split("\n")

    private val exampleInput2 = """R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day9 = Day09(exampleInput)
        assertEquals(13, day9.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day9 = Day09(resourceAsList("aoc2022/day09.txt"))
        assertEquals(6090, day9.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day9 = Day09(exampleInput2)
        assertEquals(36, day9.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day9 = Day09(resourceAsList("aoc2022/day09.txt"))
        assertEquals(2566, day9.solvePart2())
    }
}
