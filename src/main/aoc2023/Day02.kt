package aoc2023

class Day02(input: List<String>) {

    private val parsedInput = input.map {
        val gameId = it.substringBefore(":").split(" ").last().toInt()
        println("GameID: $gameId")
        val rawThrows = it.substringAfter(": ")
        val reveals = rawThrows.split("; ")
        val cubesInReveals = reveals.map {
            it.split(", ").map {
                val reveal = it.split(" ")
                reveal.first().toInt() to reveal.last()
            }
        }
        Game(gameId, cubesInReveals)
    }

    data class Game(
        val id: Int,
        val throws: List<List<Pair<Int, String>>>
    )

    // Rules: only 12 red cubes, 13 green cubes, and 14 blue cubes
    fun solvePart1(): Int {
        val result = parsedInput.sumOf { game ->

            val possible = game.throws.all { t ->

                t.all { t1 ->
                    when {
                        t1.second == "red" -> t1.first <= 12
                        t1.second == "green" -> t1.first <= 13
                        t1.second == "blue" -> t1.first <= 14
                        else -> {
                            println("Lel"); false
                        }
                    }
                }
            }
            if (possible) game.id else 0
        }

        return result
    }


    fun solvePart2(): Int {

        return parsedInput.sumOf { game ->
            val flat = game.throws.flatten()

            val r = flat.filter { it.second == "red" }.maxOf { it.first }
            val g = flat.filter { it.second == "green" }.maxOf { it.first }
            val b = flat.filter { it.second == "blue" }.maxOf { it.first }

            r * g * b
        }
    }
}