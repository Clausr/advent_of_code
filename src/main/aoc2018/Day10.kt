package aoc2018

class Day10(input: List<String>) {

    private val lights = input.map(Light::from)

    fun solvePart1(): String {
        val message = Sky(lights).getMessageWithLeastArea()
        println(message)

        return message
    }

    fun solvePart2(): String {
        return ""
    }

    data class Light(var x: Int, var y: Int, val velocityX: Int, val velocityY: Int) {

        fun move(direction: Direction) = when (direction) {
            Direction.Forward -> {
                x += velocityX
                y += velocityY
            }

            Direction.Backward -> {
                x -= velocityX
                y -= velocityY
            }
        }

        companion object {
            fun from(line: String): Light {
                val (x, y) = line.substringAfter("<").substringBefore(">").trimAndSplitBy(",")
                val (velocityX, velocityY) = line.substringAfterLast("<").substringBeforeLast(">").trimAndSplitBy(",")

                return Light(x.toInt(), y.toInt(), velocityX.toInt(), velocityY.toInt())
            }
        }
    }

    enum class Direction {
        Forward, Backward
    }

    data class Sky(val lights: List<Light>) {

        /**
         * For this to make sense, we assume that the message shows itself when the area of all the lights are the smallest
         * This might be naïve, but it seems to work with this task.
         * It would've failed if the stars all started from the center of the sky
         */
        fun getMessageWithLeastArea(): String {
            var lastArea = Long.MAX_VALUE
            var currentArea = calculateCurrentArea()

            while (lastArea > currentArea) {
                moveLights()

                lastArea = currentArea
                currentArea = calculateCurrentArea()
            }

            // We're out of the loop since the lastArea is smaller than the current area,
            // so that means we're 1 second too far ahead... Luckily we can just go back in time.
            moveLights(direction = Direction.Backward)

            return this.toString()
        }

        private fun moveLights(direction: Direction = Direction.Forward) = lights.forEach { it.move(direction) }

        private val rangeY
            get() = IntRange(lights.minOf { it.y }, lights.maxOf { it.y })
        private val rangeX
            get() = IntRange(lights.minOf { it.x }, lights.maxOf { it.x })

        private fun calculateCurrentArea(): Long = (rangeX.last - rangeX.first).toLong() * (rangeY.last - rangeY.first).toLong()

        override fun toString(): String {
            val lightSet = lights.map { it.x to it.y }.toSet()

            return rangeY.joinToString(separator = "\n") { y ->
                rangeX.map { x ->
                    if (x to y in lightSet) '#' else '.'
                }.joinToString("")
            }
        }
    }
}

private fun String.trimAndSplitBy(delimiter: String) = replace("\\s".toRegex(), "").split(delimiter)
