package aoc2022

class Day11(input: List<String>) {
    private val monkeys = input.parseMonkeys()

    fun solvePart1(): Long {
        rounds(numRounds = 20) { it / 3 }
        return monkeys.business()
    }

    fun solvePart2(): Long {
        val testProduct = monkeys.map { it.test }.reduce(Long::times)
        rounds(numRounds = 10_000) { it % testProduct }
        return monkeys.business()
    }

    private fun List<Monkey>.business(): Long =
        sortedByDescending { it.interactions }.let { it[0].interactions * it[1].interactions }

    private fun rounds(numRounds: Int, changeToWorryLevel: (Long) -> Long) {
        repeat(numRounds) {
            monkeys.forEach { it.inspectItems(monkeys, changeToWorryLevel) }
        }
    }

    private fun List<String>.parseMonkeys(): List<Monkey> {
        return chunked(7).map { attribute ->

            val operationValue = attribute[2].substringAfterLast(" ")
            val operation: (Long) -> Long = when {
                operationValue == "old" -> ({ it * it })
                '*' in attribute[2] -> ({ it * operationValue.toLong() })
                else -> ({ it + operationValue.toLong() })
            }

            Monkey(
                items = attribute[1].substringAfter(": ").split(", ").map { it.toLong() }.toMutableList(),
                test = attribute[3].substringAfterLast(" ").toLong(),
                operation = operation,
                testTrue = attribute[4].split(" ").last().toInt(),
                testFalse = attribute[5].split(" ").last().toInt()
            )
        }
    }
}

data class Monkey(
    val items: MutableList<Long>, //Worry level
    val operation: (Long) -> Long,
    val test: Long,
    val testTrue: Int,
    val testFalse: Int
) {
    var interactions: Long = 0

    fun inspectItems(monkeys: List<Monkey>, changeToWorryLevel: (Long) -> Long) {
        items.forEach { item ->
            val worry = changeToWorryLevel(operation(item))
            val target = if (worry % test == 0L) testTrue else testFalse
            monkeys[target].items.add(worry)
        }
        interactions += items.size
        items.clear()
    }
}