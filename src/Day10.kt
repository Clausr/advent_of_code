val instructions by lazy {
//    val input = readInput("Day10_test")
    val input = readInput("Day10")
    parseInstructions(input)
}

private fun List<Instruction>.toCycleMap(): Map<Int, Int> {
    val xMap = mutableMapOf(Pair(0, 1))

    forEach { instruction ->
        for (cycle in 1..instruction.cycles) {
            val prevValue = xMap[xMap.keys.size - 1] ?: throw IllegalArgumentException("")
            if (cycle == instruction.cycles && instruction.value != null) {
                xMap[xMap.keys.size] = prevValue + instruction.value
            } else {
                xMap[xMap.keys.size] = prevValue
            }
        }
    }
    return xMap
}

private fun part1(): Int {
    val xMap = instructions.toCycleMap()

    //the cycle number multiplied by the value of the X register
    var signalStrength = 0
    for (cycle in 20..220 step 40) {
        val signal = xMap[cycle - 1] ?: 0
        signalStrength += cycle * signal
    }

    return signalStrength
}

//CRT
private fun part2(): List<String> {
    val cycleMap = instructions.toCycleMap()

    val crtMap = mutableMapOf<Int, String>()
    cycleMap.forEach { (cycle, x) ->
        val row = (cycle / 40)

        val crtLine = MutableList(40) { '.' }

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
            crtMap[row] += "."
        }
    }

    return crtMap.values.toList()
}

private fun parseInstructions(input: List<String>): List<Instruction> = input.map {
    val split = it.split(" ")
    if (split.size > 1) {
        Instruction(2, split.last().toInt())
    } else {
        //noop
        Instruction(1)
    }
}

data class Instruction(val cycles: Int, val value: Int? = null)

fun main() {
    println("part1(): ${part1()}")

    println("Part 2")
    part2().forEach {
        println(it)
    }
}