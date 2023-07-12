package aoc2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day05Test {
    private val exampleInput = """    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2""".split("\n")

    @Test
    fun testPartOneExample1() {
        val day5 = Day05(exampleInput)
        assertEquals("CMZ", day5.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day5 = Day05(resourceAsList("aoc2022/day05.txt"))
        assertEquals("TLNGFGMFN", day5.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day5 = Day05(exampleInput)
        assertEquals("MCD", day5.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day5 = Day05(resourceAsList("aoc2022/day05.txt"))
        assertEquals("FGLQJCMBD", day5.solvePart2())
    }
}
