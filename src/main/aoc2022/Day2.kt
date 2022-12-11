package aoc2022

class Day2(input: List<String>) {

    val input = parseStrategyToList(input)

    enum class Result(val letter: String, val value: Int) {
        Loss("X", 0),
        Draw("Y", 3),
        Win("Z", 6);

        companion object {
            fun parseToResult(letter: String): Result = values().first { it.letter == letter }
        }
    }

    enum class Hand(val firstColumn: String, val secondColumn: String, val value: Int) {
        Rock(firstColumn = "A", secondColumn = "X", value = 1),
        Paper(firstColumn = "B", secondColumn = "Y", value = 2),
        Scissors(firstColumn = "C", secondColumn = "Z", value = 3);

        companion object {
            fun parseFirstToHand(firstColumn: String): Hand = values().first { it.firstColumn == firstColumn }
            fun parseSendToHand(secondColumn: String): Hand = values().first { it.secondColumn == secondColumn }

            fun playHand(hands: Pair<Hand, Hand>): Int {
                val loss = 0
                val draw = 3
                val win = 6

                val gameResult =
                    when (hands.second) {
                        Rock -> when (hands.first) {
                            Rock -> draw
                            Paper -> loss
                            Scissors -> win
                        }

                        Paper -> when (hands.first) {
                            Rock -> win
                            Paper -> draw
                            Scissors -> loss
                        }

                        Scissors -> when (hands.first) {
                            Rock -> loss
                            Paper -> win
                            Scissors -> draw
                        }
                    }

                return gameResult + hands.second.value
            }

            fun playHandV2(hands: Pair<Hand, Hand>): Int {
                // X == Loose
                // Y == Draw
                // Z == Win
                val result = Result.parseToResult(hands.second.secondColumn)

                val handAsDictated = when (result) {
                    Result.Loss -> when (hands.first) {
                        Rock -> Scissors
                        Paper -> Rock
                        Scissors -> Paper
                    }

                    Result.Draw -> hands.first
                    Result.Win -> when (hands.first) {
                        Rock -> Paper
                        Paper -> Scissors
                        Scissors -> Rock
                    }
                }
//                println("Result ${result.value} - hand value: ${handAsDictated.value}")
                return handAsDictated.value + result.value
            }
        }
    }

    fun parseStrategyToList(strategyGuide: List<String>): List<Pair<Hand, Hand>> {
        val hands = strategyGuide
            .map {
                val split = it.split(" ")
                Pair(Hand.parseFirstToHand(split.first()), Hand.parseSendToHand(split[1]))
            }

        return hands
    }

    fun solvePart1(): Int {
        return input.map { Hand.playHand(it) }.sum()
    }

    fun solvePart2(): Int {
        return input.map { Hand.playHandV2(it) }.sum()
    }
}