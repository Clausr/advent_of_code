package aoc2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList

class Day07Test {
    private val exampleInput = """$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k""".trimIndent().split("\n")

    @Test
    fun testPartOneExample1() {
        val day7 = Day07(exampleInput)
        assertEquals(95437, day7.solvePart1())
    }

    @Test
    fun partOneRealInput() {
        val day7 = Day07(resourceAsList("aoc2022/day07.txt"))
        assertEquals(1989474, day7.solvePart1())
    }

    @Test
    fun testPartTwoExample1() {
        val day7 = Day07(exampleInput)
        assertEquals(24933642, day7.solvePart2())
    }

    @Test
    fun partTwoRealInput() {
        val day7 = Day07(resourceAsList("aoc2022/day07.txt"))
        assertEquals(1111607, day7.solvePart2())
    }
}
