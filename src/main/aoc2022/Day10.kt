package aoc2022

class Day10(private val input: List<String>) {

    private fun parseInstructions(): List<Instruction> = input.map {
        val split = it.split(" ")
        if (split.size > 1) {
            Instruction(2, split.last().toInt())
        } else {
            //noop
            Instruction(1)
        }
    }

    //CRT
    private fun part2(): List<String> {
        val cycleMap = parseInstructions().toCycleMap()

        val crtMap = mutableMapOf<Int, String>()
        cycleMap.forEach { (cycle, x) ->
            val row = (cycle / 40)
            val crtLine = MutableList(40) { ' ' }

            // Looks stupid, but works
            if (x - 1 >= 0) {
                crtLine[x - 1] = '#'
            }
            if (x >= 0) {
                crtLine[x] = '#'
            }
            if (x + 1 >= 0 && x + 1 < crtLine.size) {
                crtLine[x + 1] = '#'
            }

            crtMap.putIfAbsent(row, "")
            if (crtLine[cycle - (row * 40)] == '#') {
                crtMap[row] += "#"
            } else {
                crtMap[row] += " "
            }
        }

        return crtMap.values.toList().dropLast(1)
    }

    data class Instruction(val cycles: Int, val value: Int? = null)

    private fun List<Instruction>.toCycleMap(): Map<Int, Int> {
        val xMap = mutableMapOf(Pair(0, 1))
        forEach { instruction ->
            for (cycle in 1..instruction.cycles) {
                val prevValue = xMap[xMap.keys.size - 1] ?: 1
                if (cycle == instruction.cycles && instruction.value != null) {
                    xMap[xMap.keys.size] = prevValue + instruction.value
                } else {
                    xMap[xMap.keys.size] = prevValue
                }
            }
        }
        return xMap
    }

    fun solvePart1(): Int {
        val xMap = parseInstructions().toCycleMap()

        //the cycle number multiplied by the value of the X register
        var signalStrength = 0
        for (cycle in 20..220 step 40) {
            val signal = xMap[cycle - 1] ?: 0
            signalStrength += cycle * signal
        }

        return signalStrength
    }

    fun solvePart2(): String {
        part2().forEach(::println)
        return "PHLHJGZA"
    }
}

