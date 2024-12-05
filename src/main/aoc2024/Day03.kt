package aoc2024

class Day03(input: List<String>) {

    private val inputJoined = input.joinToString("")
    private fun calcMultLine(line: String): Int {
        val regex = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()

        val regFinds = regex.findAll(line).map {
            it.value
        }

        val numbersMatcher = "\\d{1,3}".toRegex()
        return regFinds.sumOf { regFind ->
            numbersMatcher.findAll(regFind)
                .zipWithNext { a, b ->
                    a.value.toInt() * b.value.toInt()
                }.first()
        }
    }

    fun solvePart1(): Int {
        return calcMultLine(inputJoined)
    }


    private fun calcWithLogic(line: String): Int {
        val someMatcher = "(mul)\\((\\d{1,3}),(\\d{1,3})\\)|(do)\\(\\)|(don't)\\(\\)".toRegex()

        val quickfixLine = "do()" + line + "don't()"

        var doThing = true
        var acc = 0
        someMatcher.findAll(quickfixLine)
            .forEach { result ->
                when (result.value) {
                    "don't()" -> {
                        doThing = false
                    }

                    "do()" -> doThing = true
                    else -> {
                        if (doThing) {
                            acc += calcMultLine(result.value)
                        }
                    }
                }
            }

        return acc
    }

    fun solvePart2(): Int {
        return calcWithLogic(inputJoined)
    }
}