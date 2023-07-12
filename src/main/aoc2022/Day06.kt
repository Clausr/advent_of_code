package aoc2022

class Day06(val input: List<String>) {

    private fun getStartOfPacketMarker(sequenceSize: Int): Int {
        val sequence = input.first().windowedSequence(sequenceSize, 1)
        return sequence.indexOfFirst { it.toSet().size == sequenceSize } + sequenceSize
    }

    fun solvePart1(): Int {
        return getStartOfPacketMarker(4)
    }

    fun solvePart2(): Int {
        return getStartOfPacketMarker(14)
    }
}
