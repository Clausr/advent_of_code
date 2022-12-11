fun main() {
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

    fun part1(input: List<String>): Int {
        val sortedMap = fillMap(input).map { it.key to it.value.sum() }.sortedByDescending { it.second }
        return sortedMap.first().second
    }

    fun part2(input: List<String>): Int {
        val filledMap = fillMap(input).map { it.key to it.value.sum() }.sortedByDescending { it.second }
        return filledMap.subList(0, 3).sumOf { it.second }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = resourceAsList("Day01_test.txt")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = resourceAsList("Day01.txt")
    println(part1(input))
    println(part2(input))
}
