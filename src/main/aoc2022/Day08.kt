typealias Column = List<Int>
typealias Row = List<Int>

data class TreeGrid(val rows: List<Row>, val columns: List<Column>)

fun mapToTreeGrid(input: List<String>): TreeGrid {
    val rows = mutableListOf<List<Int>>()
    val columns = mutableListOf<List<Int>>()

    // Rows
    input.forEach { horizontal ->
        rows.add(
            horizontal.map {
                it.digitToInt()
            })
    }

    for (columnIndex in input.indices) {
        columns.add(rows.map {
            it[columnIndex]
        })
    }

    return TreeGrid(rows, columns)
}

private fun calculateVisibleTrees(treeGrid: TreeGrid): Int {
    val coordinateSet = mutableSetOf<Pair<Int, Int>>()
    // Get borders
    treeGrid.rows.forEachIndexed outer@{ y, row ->
        if (y == 0 || y == treeGrid.rows.size - 1) {
            coordinateSet.addAll(List(row.size) { x -> Pair(x, y) })
        } else {
//            //Do actual calculations thing
//            //We need to traverse both from right and left (reversed)
            var currentMax = row.first()
            row.forEachIndexed inner@{ x, tree ->
                if (tree > currentMax) {
                    currentMax = tree
                    coordinateSet.add(Pair(x, y))
                } else {
                    return@inner
                }
            }

            // Swap directions
            val reversedRow = row.reversed()
            currentMax = reversedRow.first()

            reversedRow.forEachIndexed { x, tree ->
                if (tree > currentMax) {
                    currentMax = tree
                    coordinateSet.add(Pair((reversedRow.size - 1) - x, y))
                } else {
                    return@forEachIndexed
                }
            }
        }
    }

    treeGrid.columns.forEachIndexed outer@{ x, column ->
        if (x == 0 || x == treeGrid.columns.size - 1) {
            coordinateSet.addAll(List(column.size) { y -> Pair(x, y) })
        } else {
            //Do actual calculations thing
            //We need to traverse both from top and bottom (reversed)
            var currentMax = column.first()
            column.forEachIndexed { y, tree ->
                if (tree > currentMax) {
                    currentMax = tree
                    coordinateSet.add(Pair(x, y))
                } else {
                    return@forEachIndexed
                }
            }

            // Reset / Reverse
            val reversedColumn = column.reversed()
            currentMax = reversedColumn.first()
            reversedColumn.forEachIndexed { y, tree ->
                if (tree > currentMax) {
                    currentMax = tree
                    coordinateSet.add(Pair(x, (reversedColumn.size - 1) - y))
                } else {
                    return@forEachIndexed
                }
            }
        }
    }

    return coordinateSet.count()
}

/**
 * stop if you reach an edge or at the first tree that is the same height or taller than the tree under consideration
 * (If a tree is right on the edge, at least one of its viewing distances will be zero.)
 */
private lateinit var grid: List<List<Int>>

private fun treeScore(x: Int, y: Int): Int {
    val treeHeight = grid[x][y]
    val row = grid[x]
    if (x == 0 || x == row.size - 1 || y == 0 || y == grid.size - 1) {
        return 0
    }

    fun getScore(treeLine: List<Int>): Int {
        var count = 0
        for (tree in treeLine) {
            count++
            if (tree >= treeHeight) {
                break
            }
        }
        return count
    }

    val left = getScore(row.subList(0, y).reversed())
    val right = getScore(row.subList(y + 1, grid.size))
    val up = getScore(grid.map { it[y] }.subList(0, x).reversed())
    val down = getScore(grid.map { it[y] }.subList(x + 1, grid.size))

    return left * right * up * down
}

private fun findScenicHighScore(): Int {
    return grid.indices.maxOf { x ->
        grid[x].indices.maxOf { y ->
            treeScore(x, y)
        }
    }
}

fun main() {
    val input = resourceAsList("Day08")

    grid = input.map { it.map { it.digitToInt() } }

    fun part1(input: List<String>): Int = calculateVisibleTrees(mapToTreeGrid(input))

    fun part2(): Int = findScenicHighScore()

    //Enter test values / types here
    val testAnswer1 = 21
    val testAnswer2 = 8

    // test if implementation meets criteria from the description, like:
    val testInput = resourceAsList("Day08_test.txt")

    check(part1(testInput) == testAnswer1)
    check(part2() == testAnswer2)

    println(part1(input))
    println("part 2: ${part2()}")
}
