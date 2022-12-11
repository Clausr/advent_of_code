package aoc2022

class Day1(input: List<String>) {
    val caloryMap = fillMap(input)

    fun fillMap(caloryText: List<String>): MutableMap<Int, MutableList<Int>> {
        val elfMap: MutableMap<Int, MutableList<Int>> = mutableMapOf()
        var currentElfIndex = 1

        caloryText.forEach {
            if (it.isBlank()) {
                currentElfIndex += 1
            }
            if (it.isNotBlank()) {
                if (!elfMap.containsKey(currentElfIndex)) {
                    elfMap[currentElfIndex] = mutableListOf(it.toInt())
                } else {
                    elfMap[currentElfIndex]?.add(it.toInt())
                }
            }
        }

        return elfMap
    }

    fun solvePart1(): Int {
        val sortedMap = caloryMap.map { it.key to it.value.sum() }.sortedByDescending { it.second }
        return sortedMap.first().second
    }

    fun solvePart2(): Int {
        val filledMap = caloryMap.map { it.key to it.value.sum() }.sortedByDescending { it.second }
        return filledMap.subList(0, 3).sumOf { it.second }
    }
}