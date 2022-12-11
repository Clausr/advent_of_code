import kotlin.math.abs

private fun part1(input: List<String>): Int {
    val movement = input.map(::movement)
    return findUniqueTailLocations(movement, children = 1)
}

private fun part2(input: List<String>): Int {
    val movement = input.map(::movement)
    return findUniqueTailLocations(movement, children = 9)
}

private fun findUniqueTailLocations(movements: List<Movement>, children: Int = 1): Int {
    val rope = (0..children).map { Location(0, 0) }.toMutableList()
    val lastVisitedTailLocation = mutableSetOf<Location>().also { it.add(rope.last()) }

    movements.forEach { movement ->
        repeat(movement.steps) {
            //Move head
            rope[0] = rope[0].move(movement.direction)

            var parentIndex = 0
            var shouldCheckChild = true
            // move child
            while (shouldCheckChild) {
                val parent = rope[parentIndex]
                val child = rope[parentIndex + 1]

                if (abs(parent.x - child.x) > 1 || abs(parent.y - child.y) > 1) {
                    rope[parentIndex + 1] = Location(
                        x = child.x + parent.x.compareTo(child.x),
                        y = child.y + parent.y.compareTo(child.y)
                    ).also {
                        if (parentIndex + 1 == children) {
                            lastVisitedTailLocation.add(it)
                            shouldCheckChild = false
                        }
                    }
                } else {
                    shouldCheckChild = false
                }

                parentIndex++
            }
        }
    }

    println("Size ${lastVisitedTailLocation.size}")
    return lastVisitedTailLocation.size
}


fun main() {
    val input = resourceAsList("Day09.txt")
    println(part1(input))
    println(part2(input))
}

private fun movement(line: String) = line.split(" ").let { (d, s) ->
    val direction = when (d) {
        "R" -> Direction.Right
        "L" -> Direction.Left
        "U" -> Direction.Up
        "D" -> Direction.Down
        else -> throw IllegalArgumentException("Invalid direction")
    }
    val steps = s.toInt()
    Movement(direction, steps)
}

data class Movement(val direction: Direction, val steps: Int)
enum class Direction {
    Left, Right, Up, Down
}

data class Location(val x: Int, val y: Int) {
    fun move(direction: Direction): Location {
        return when (direction) {
            Direction.Left -> Location(x - 1, y)
            Direction.Right -> Location(x + 1, y)
            Direction.Up -> Location(x, y + 1)
            Direction.Down -> Location(x, y - 1)
        }
    }
}