package aoc2024

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day03Test {
    private val exampleInput = """xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
""".trimIndent().split("\n")

    private val exampleInput2 = """xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day3 = Day03(exampleInput)
        assertEquals(161, day3.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day3 = Day03(resourceAsList("aoc2024/day03.txt"))
        assertEquals(181345830, day3.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day3 = Day03(exampleInput2)
        assertEquals(48, day3.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day3 = Day03(resourceAsList("aoc2024/day03.txt"))
        assertEquals(98729041, day3.solvePart2())
    }
}