package aoc2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsString

class Day13Test {
    private val exampleInput = """[1,1,3,1,1]
[1,1,5,1,1]

[[1],[2,3,4]]
[[1],4]

[9]
[[8,7,6]]

[[4,4],4,4]
[[4,4],4,4,4]

[7,7,7,7]
[7,7,7]

[]
[3]

[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]""".trimIndent()

    @Test
    fun testPartOneExample1() {
        val day13 = Day13(exampleInput)
        assertEquals(13, day13.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day13 = Day13(resourceAsString("2022/day13.txt"))
        assertEquals(0, day13.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day13 = Day13(exampleInput)
        assertEquals(140, day13.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day13 = Day13(resourceAsString("2022/day13.txt"))
        assertEquals(0, day13.solvePart2())
    }
}