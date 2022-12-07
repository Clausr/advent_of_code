fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    //Enter test values / types here
    val testAnswer1: Int? = null
    val testAnswer2: Int? = null

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    testAnswer1?.let { check(part1(testInput) == it) }
    testAnswer2?.let { check(part2(testInput) == it) }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
