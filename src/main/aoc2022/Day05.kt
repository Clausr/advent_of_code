package aoc2022

class Day05(input: List<String>) {

    private val stacks = getStacks(input)
    private val instructions = getInstructions(input)

    private fun getStacks(input: List<String>): Map<Int, MutableList<Char>> {
        val containerPart = input.takeWhile { it.isNotBlank() }

        val indexOfChars = containerPart.reversed().first().mapIndexedNotNull { index, c -> if (c.isDigit()) index else null }

        val map = mutableMapOf<Int, MutableList<Char>>()
        val containersOnly = containerPart.reversed().drop(1)
        containersOnly.map { stackString ->
            println(stackString)

            indexOfChars.forEachIndexed { index, i ->
                map[index + 1] = containersOnly.mapNotNull { containerLine ->
                    val char = containerLine.getOrNull(i)
                    if (char?.isLetter() == true) {
                        char
                    } else null
                }.toMutableList()
            }
        }
        return map.toMap()
    }

    data class Instruction(val quantity: Int, val from: Int, val to: Int)

    private fun getInstructions(input: List<String>): List<Instruction> {
        val cutoff = input.indexOfFirst { it.isBlank() }

        val lastLines = input.drop(cutoff + 1)

        val words = lastLines.map { it.split(" ") }.filter { it.isNotEmpty() }

        return words.map {
            Instruction(it[1].toInt(), it[3].toInt(), it[5].toInt())
        }
    }

    fun solvePart1(): String {
        instructions.forEach { instruction ->
            // Move one at a time
            (1..instruction.quantity).forEach {
                val lastOfFrom = stacks[instruction.from]?.removeLast()!!
                stacks[instruction.to]?.add(lastOfFrom)
            }
        }

        return stacks.values.joinToString("") { it.last().toString() }
    }

    fun solvePart2(): String {
        instructions.forEach { instruction ->
            // Move multiple at a time
            val craneHolding = stacks[instruction.from]?.takeLast(instruction.quantity)!!

            stacks[instruction.to]?.addAll(craneHolding)

            (1..instruction.quantity).forEach {
                stacks[instruction.from]?.removeLast()
            }
        }

        return stacks.values.joinToString("") { it.last().toString() }
    }
}
