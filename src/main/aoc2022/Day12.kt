package aoc2022

import java.util.*


typealias Coordinates = Pair<Int, Int>

private fun Coordinates.getNeighbors(): List<Coordinates> {
    val leftOf = Coordinates(first - 1, second)
    val rightOf = Coordinates(first + 1, second)
    val topOf = Coordinates(first, second - 1)
    val bottomOf = Coordinates(first, second + 1)

    return listOfNotNull(leftOf, rightOf, topOf, bottomOf)
}

class Day12(input: List<String>) {
    private val heightMap = input.parseHeightMap()

    data class HeightMap(val elevations: Map<Coordinates, Int>, val start: Coordinates, val end: Coordinates) {

        fun shortestPath(
            start: Coordinates,
            isGoal: (Coordinates) -> Boolean,
            canMove: (Int, Int) -> Boolean
        ): Int {
            val seen = mutableSetOf<Coordinates>()
            val prioQueue = PriorityQueue<PathCost>().apply { add(PathCost(start, 0)) }

            while (prioQueue.isNotEmpty()) {
                val nextCoord = prioQueue.poll()

                if (nextCoord.coordinates !in seen) {
                    seen += nextCoord.coordinates
                    val neighbors = nextCoord.coordinates.getNeighbors()
                        .filter { it in elevations }
                        .filter { canMove(elevations.getValue(nextCoord.coordinates), elevations.getValue(it)) }

                    if (neighbors.any { isGoal(it) }) return nextCoord.cost + 1
                    prioQueue.addAll(neighbors.map { PathCost(it, nextCoord.cost + 1) })
                }
            }
            throw IllegalStateException("No path...")
        }
    }

    private class PathCost(val coordinates: Coordinates, val cost: Int) : Comparable<PathCost> {
        override fun compareTo(other: PathCost): Int {
            return this.cost.compareTo(other.cost)
        }
    }

    private fun List<String>.parseHeightMap(): HeightMap {
        var start: Coordinates? = null
        var end: Coordinates? = null

        val elevations = this.flatMapIndexed { y, row ->
            row.mapIndexed { x, char ->
                val here = Coordinates(x, y)
                here to when (char) {
                    'S' -> 0.also { start = here }
                    'E' -> 25.also { end = here }
                    else -> char - 'a'
                }
            }
        }.toMap()

        return HeightMap(elevations, start!!, end!!)
    }

    fun solvePart1(): Int {
        return heightMap.shortestPath(
            start = heightMap.start,
            isGoal = { it == heightMap.end },
            canMove = { from, to -> to - from <= 1 }
        )
    }

    fun solvePart2(): Int {
        return heightMap.shortestPath(
            start = heightMap.end,
            isGoal = { heightMap.elevations[it] == 0 },
            canMove = { from, to -> from - to <= 1 }
        )
    }
}