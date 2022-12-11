package aoc2022

class Day3(input: List<String>) {
    val doStuff = doStuff(input)
    val doStuff2 = doStuff2(input)

    fun doStuff(rucksack: List<String>): List<Rucksack> {
        return rucksack.map {
            Rucksack(it.substring(0, (it.length) / 2), it.substring((it.length) / 2, it.length))
        }
    }

    fun doStuff2(rucksack: List<String>): List<List<String>> {
        var i = 0
        val groups = mutableListOf<List<String>>()

        for (i in 3..rucksack.size step 3) {
            val sublist = rucksack.subList(i - 3, i)
            groups.add(sublist)
        }

        return groups
    }


    fun <T> findCommon(first: List<T>, second: List<T>, third: List<T>): Set<T> {
        return first.filter { second.contains(it) && third.contains(it) }.toSet()
    }

    fun getSharedLetterForGroup(group: List<String>): Char {
        return findCommon(group[0].toList(), group[1].toList(), group[2].toList()).first()
    }

    private fun sumOfPriorities(rucksack: List<List<String>>): List<Char> {

        return rucksack.map { getSharedLetterForGroup(it) }
    }


    val upperCaseLetters: Map<Char, Int> by lazy {
        val letterList = mutableMapOf<Char, Int>()

        var c = 'A'
        var i = 27
        while (c <= 'Z') {
            letterList.putIfAbsent(c, i)
            i += 1
            ++c
        }
        letterList
    }
    val lowerCaseLetters: Map<Char, Int> by lazy {
        val letterList = mutableMapOf<Char, Int>()
        var c = 'a'
        var i = 1
        while (c <= 'z') {
            letterList.putIfAbsent(c, i)
            i += 1
            ++c
        }
        letterList
    }

    data class Rucksack(val firstCompartment: String, val secondCompartment: String) {
        fun getSharedChar(): Char? {
            var currentLetter: Char? = null
            firstCompartment.forEach { first ->
                if (secondCompartment.any { second -> second.equals(first) }) {
                    currentLetter = first
                }
            }
            return currentLetter
        }
    }


    fun solvePart1(): Int {
        return doStuff.sumOf {
            val value = it.getSharedChar()?.let {
                if (it.isLowerCase()) {
                    lowerCaseLetters[it]
                } else {
                    upperCaseLetters[it]
                }
            } ?: 0
            value
        }

    }

    fun solvePart2(): Int {
        return sumOfPriorities(doStuff2).sumOf {
            if (it.isLowerCase()) {
                lowerCaseLetters[it]
            } else {
                upperCaseLetters[it]
            } ?: 0
        }
    }
}