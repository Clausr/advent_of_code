package aoc2024

class Day09(input: List<String>) {

    private val diskMap = input.first().map { it.toString().toInt() }

    private fun Iterable<Int>.splitDiskMap(): Pair<List<Int>, List<Int>> {
        val files = ArrayList<Int>()
        val freeSpace = ArrayList<Int>()

        for ((index, element) in this.withIndex()) {
            if (index % 2 == 0) {
                files.add(element)
            } else {
                freeSpace.add(element)
            }
        }

        return Pair(files, freeSpace)
    }

    //    private fun test(): Int {
//        val (files, freeSpace) = diskMap.splitDiskMap()
//
//        val filesSpread = files.mapIndexed { id, i ->
//            (1..i).map {
//                id
//            }
//        }.flatten().toMutableList()
//
//        filesSpread.forEachIndexed { index, i ->
//            filesSpread.removeAt(filesSpread.size - 1 - index)
//        }
//        return -1
//    }
    private fun createDiskMap(): List<Int> {
        val (files, freeSpace) = diskMap.splitDiskMap()
        val refragmented = mutableListOf<Int>()

        files.forEachIndexed { id, fileSize ->
            (1..fileSize).forEach { _ ->
                refragmented.add(id)
            }
            if (id < freeSpace.size) {
                repeat(freeSpace[id]) {
                    refragmented.add(-1)
                }
            }
        }
        return refragmented
    }

    fun solvePart1(): Long {
        val refragmented = createDiskMap().toMutableList()

        while (refragmented.any { it == -1 }) {
            val firstFreeSpace = refragmented.indexOfFirst { it == -1 }
            val lastRemoved = refragmented.removeLast()
            refragmented[firstFreeSpace] = lastRemoved
        }

        val checksum: Long = refragmented.mapIndexed { index, id ->
            (index * id).toLong()
        }.sum()

        return checksum
    }

    fun solvePart2(): Long {
        val diskMap = createDiskMap()
        val (files, freeSpace) = diskMap.splitDiskMap()


        files.forEach {
            if (it > 0) {
                val freeBlock = findFreeBlock(fileSize = it, diskMap = diskMap)

                println("Freeblock for $it : $freeBlock")
            }
        }
//        println(fragmentedDisk)
//        val filesReversed = files.reversed()
//        val freespaceMutable = freeSpace.toMutableList()
//        println("initial freespace: $freespaceMutable")

//        filesReversed.forEachIndexed { index, fileSize ->
//            val indexWithSpace = freespaceMutable.indexOfFirst { fileSize <= it }
//            println("Filesize: $fileSize -- index thing: ${filesReversed.size - 1 - index} -- Index: $indexWithSpace")
//            if (indexWithSpace != -1 && freespaceMutable.size > index) {
//
//                val newSpace = freespaceMutable[index] - fileSize
//
//                println("oldSpace = ${freespaceMutable[index]} - New space: $newSpace")
//                freespaceMutable[index] = newSpace
//                println("New space: ${freespaceMutable[index]}")
//                println(freespaceMutable)
//            }
//        }
//        println("after thing: $freespaceMutable")
//
//        println(fragmentedDisk)

        solvePart22()

        return -1
    }

    fun solvePart22(): Long {
        val (files, freeSpace) = diskMap.splitDiskMap()

        val refragmented = mutableListOf<Int>()

        files.forEachIndexed { id, fileSize ->
            (1..fileSize).forEach { _ ->
                refragmented.add(id)
            }
            if (id < freeSpace.size) {
                repeat(freeSpace[id]) {
                    refragmented.add(-1)
                }
            }
        }

        println("Fragmented: $refragmented")

        freeSpace.forEach { space ->
            val indexThatFits = files.reversed().indexOfFirst { file ->
                println("indexOfFirst: ${file <= space}")
                file <= space
            }
            if (indexThatFits != -1) {
                val sublist = refragmented.subList(indexThatFits, indexThatFits + space)
                println("File fits into index $indexThatFits -- sublist: $sublist")
            }
        }

        while (refragmented.any { it == -1 }) {
            val firstFreeSpace = refragmented.indexOfFirst { it == -1 }
            val lastRemoved = refragmented.removeLast()
            refragmented[firstFreeSpace] = lastRemoved
        }

        val checksum: Long = refragmented.mapIndexed { index, id ->
            (index * id).toLong()
        }.sum()

        return checksum
    }

    fun findFreeBlock(fileSize: Int, diskMap: List<Int>): Int {
        return diskMap.windowed(fileSize).indexOfFirst { it.all { it == -1 } }
    }
}