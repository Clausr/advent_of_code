package aoc2022

class Day07(val input: List<String>) {
    private fun parseCommands(): MutableSet<Directory> {
        val rootDirectory = Directory("/", null)
        val allDirectories = mutableSetOf<Directory>().also { it.add(rootDirectory) }

        var currentDirectory = rootDirectory

        input.forEach { line ->
            if (line.startsWith("$")) {
                val commandSplit = line.substringAfter("$ ").split(" ")

                if (commandSplit.first() == "cd") {
                    val location = commandSplit[1]
                    currentDirectory = when (location) {
                        "/" -> rootDirectory
                        ".." -> currentDirectory.parent!!
                        else -> currentDirectory.directories[location]!!
                    }
                }
            } else {
                //Inside dir (after ls)
                val split = line.split(" ")
                if (split.first() == "dir") {
                    // Create folder
                    val dir = Directory(split[1], currentDirectory)
                    allDirectories.add(dir)
                    currentDirectory.directories.putIfAbsent(split[1], dir)
                } else {
                    currentDirectory.files.putIfAbsent(split[1], File1(split[1], size = split[0].toLong()))
                }
            }
        }

        return allDirectories
    }

    data class Directory(val id: String, val parent: Directory?) {
        val files = mutableMapOf<String, File1>()
        val directories = mutableMapOf<String, Directory>()

        val size: Long
            get() = files.values.sumOf { it.size } + directories.values.sumOf { it.size }
    }

    data class File1(val name: String, val size: Long)

    /**
     * Find all the directories with a total size of at most 100000.
     * What is the sum of the total sizes of those directories?
     */
    fun solvePart1(): Long {
        return parseCommands()
            .filter { it.size <= 100_000 }
            .sumOf { it.size }
    }

    /**
     * The total disk space available to the filesystem is 70000000.
     * To run the update, you need unused space of at least 30000000.
     * You need to find a directory you can delete that will free up enough space to run the update.
     * Find the smallest directory that, if deleted, would free up enough space on the filesystem to run the update.
     *
     * What is the total size of that directory?
     */
    fun solvePart2(): Long {
        val fileSystemSize = 70_000_000
        val neededSpace = 30_000_000
        val allDirectories = parseCommands()

        val currentFree = fileSystemSize - allDirectories.first { it.id == "/" }.size
        val freeSpaceNeeded = neededSpace - currentFree

        return allDirectories.filter { it.size > freeSpaceNeeded }.minOf { it.size }
    }
}
