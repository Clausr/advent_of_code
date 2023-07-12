import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.result.Result
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val year = 2022
    val day = 4

    readInputFileFromInternet(year, day)
    createDayClassFile(year, day)
    createTestFile(year, day)
}

fun Int.padWithStartingZero(): String = toString().padStart(2, '0')

fun readInputFileFromInternet(year: Int, day: Int) {
    val fileName = "day${day.padWithStartingZero()}.txt"
    val directory = "src/test/resources/aoc$year"
    val path = "$directory/$fileName"

    // Create directory
    Files.createDirectories(Paths.get(directory))

    val file = File(path).also {
        if (it.exists()) {
            println("Input file download aborted, file already exists")
            return
        }
    }

    val sessionCookie = resourceAsString("session_cookie.txt")
    Fuel.get("https://adventofcode.com/$year/day/$day/input")
        .header(Headers.USER_AGENT to "github.com/Clausr/advent_of_code/tree/main/src/main.kt by clausr")
        .header(Headers.COOKIE to "session=${sessionCookie.trimIndent()}")
        .responseString { _, response, result ->
            when (result) {
                is Result.Failure -> {
                    println("Failed to download input: ${response.statusCode} ${response.responseMessage}")
                }

                is Result.Success -> {
                    // Make sure to use same line separators as the system
                    result.value
                        .replace("\n", System.getProperty("line.separator"))
                        .also { file.writeText(it) }
                    println("$path downloaded successfully")
                }
            }
        }
        .join()
}

fun createTestFile(year: Int, day: Int) {
    val fileName = "Day${day.padWithStartingZero()}Test.kt"
    val directory = "src/test/aoc$year"

    Files.createDirectories(Paths.get(directory))

    val path = "$directory/$fileName"
    File(path)
        .takeIf { !it.exists() }
        ?.writeText(getTestCaseContent(year, day))
        ?.also { println("$path created") }
}

fun createDayClassFile(year: Int, day: Int) {
    val directory = "src/main/aoc$year"
    val fileName = "Day${day.padWithStartingZero()}.kt"
    Files.createDirectories(Paths.get(directory))

    val path = "$directory/$fileName"
    File(path)
        .takeIf { !it.exists() }
        ?.writeText(getDayClassContent(year, day))
        ?.also { println("$path created") }
}

fun getTestCaseContent(year: Int, day: Int): String {
    val doubleTriple = "\"\"\"\"\"\""
    val dayPadded = day.padWithStartingZero()
    return """package aoc$year
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import resourceAsList
class Day${dayPadded}Test {
    private val exampleInput = $doubleTriple.trimIndent().split("\n")
    @Test
    fun testPartOneExample1() {
        val day$day = Day$dayPadded(exampleInput)
        assertEquals(0, day$day.solvePart1())
    }
    @Test
    fun partOneRealInput() {
        val day$day = Day$dayPadded(resourceAsList("aoc${year}/day${dayPadded}.txt"))
        assertEquals(0, day$day.solvePart1())
    }
    @Test
    fun testPartTwoExample1() {
        val day$day = Day$dayPadded(exampleInput)
        assertEquals(0, day$day.solvePart2())
    }
    @Test
    fun partTwoRealInput() {
        val day$day = Day$dayPadded(resourceAsList("aoc${year}/day${dayPadded}.txt"))
        assertEquals(0, day$day.solvePart2())
    }
}""".replace("\n", System.getProperty("line.separator"))
}

fun getDayClassContent(year: Int, day: Int): String {
    return """package aoc${year}
class Day${day.padWithStartingZero()}(input: List<String>) {
    fun solvePart1(): Int {
        return -1
    }
    fun solvePart2(): Int {
        return -1
    }
}""".replace("\n", System.getProperty("line.separator"))
}
