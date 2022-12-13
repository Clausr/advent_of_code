package aoc2022

class Day13(val input: String) {

    sealed class Packets : Comparable<Packets> {
        data class Single(val value: Int) : Packets()

        data class Multi(val packets: List<Packets>) : Packets()

        private fun Single.toList() = Multi(listOf(this))

        override fun compareTo(other: Packets): Int {
            when {
                this is Single && other is Single -> {
                    return this.value.compareTo(other.value)
                }

                this is Multi && other is Multi -> {
                    repeat(minOf(this.packets.size, other.packets.size)) { i ->
                        val comparison = this.packets[i].compareTo(other.packets[i])
                        if (comparison != 0) {
                            return comparison
                        }
                    }
                    return this.packets.size.compareTo(other.packets.size)
                }

                else -> when {
                    this is Single -> return this.toList().compareTo(other)
                    other is Single -> return this.compareTo(other.toList())
                }
            }
            throw IllegalStateException("Bad comparison: $this :: $other")
        }

        companion object {
            fun parse(input: String): Packets {
                val list = input.substring(1, input.lastIndex)

                return Multi(
                    if (list.isEmpty()) {
                        emptyList()
                    } else {
                        buildList {
                            var index = 0
                            while (index < list.length) {
                                if (list[index] == '[') {
                                    val parenthesis = ArrayDeque<Unit>()
                                    parenthesis.addLast(Unit)
                                    var p = index + 1
                                    while (parenthesis.isNotEmpty()) {
                                        if (list[p] == '[') {
                                            parenthesis.addLast(Unit)
                                        } else if (list[p] == ']') {
                                            parenthesis.removeLast()
                                        }
                                        p++
                                    }
                                    add(parse(list.substring(index, p)))
                                    index = p + 1
                                } else {
                                    var nextIndex = list.indexOf(',', startIndex = index + 1)
                                    if (nextIndex == -1) {
                                        nextIndex = list.lastIndex + 1
                                    }
                                    add(Single(list.substring(index, nextIndex).toInt()))
                                    index = nextIndex + 1
                                }
                            }
                        }
                    }
                )
            }
        }
    }

    private fun String.parseInputPairs(): List<Pair<Packets, Packets>> {
        val pairs = this.split("\n\n").map { it.split("\n") }

        return pairs.map { Pair(Packets.parse(it[0]), Packets.parse(it[1])) }
    }

    private fun String.parseInput(): List<Packets> {
        return this.split("\n").filter(String::isNotEmpty).map { Packets.parse(it) }
    }

    fun solvePart1(): Int {
        val res = input.parseInputPairs().mapIndexed { index, pair ->
            if (pair.first < pair.second) {
                index + 1
            } else {
                0
            }
        }.sum()
        return res
    }

    fun solvePart2(): Int {
        val firstDivider = Packets.parse("[[2]]")
        val secondDivider = Packets.parse("[[6]]")
        val packets = input.parseInput().plus(listOf(firstDivider, secondDivider)).sorted()

        return (packets.indexOf(firstDivider) + 1) * (packets.indexOf(secondDivider) + 1)
    }
}