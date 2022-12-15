package aoc2022
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList
class Day14Test {
    private val exampleInput = """498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9""".trimIndent().split("\n")
    @Test
    fun testPartOneExample1() {
        val day14 = Day14(exampleInput)
        assertEquals(24, day14.solvePart1())
    }
    @Test
    fun partOneRealInput() {
        val day14 = Day14(resourceAsList("2022/day14.txt"))
        assertEquals(0, day14.solvePart1())
    }
    @Test
    fun testPartTwoExample1() {
        val day14 = Day14(exampleInput)
        assertEquals(93, day14.solvePart2())
    }
    @Test
    fun partTwoRealInput() {
        val day14 = Day14(resourceAsList("2022/day14.txt"))
        assertEquals(0, day14.solvePart2())
    }
}