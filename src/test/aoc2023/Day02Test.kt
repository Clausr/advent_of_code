package aoc2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day02Test {
    private val exampleInput = """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day2 = Day02(exampleInput)
        assertEquals(8, day2.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day2 = Day02(resourceAsList("aoc2023/day02.txt"))
        assertEquals(2169, day2.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day2 = Day02(exampleInput)
        assertEquals(2286, day2.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day2 = Day02(resourceAsList("aoc2023/day02.txt"))
        assertEquals(60948, day2.solvePart2())
    }
}