package day07

import utils.stack.pop
import utils.stack.push

fun findSmallerSizeDirectorySum(inputPath: String): Int =
    calculateDirSizes(inputPath)
        .filter { (_, size) -> size <= 100000 }
        .map { (_, size) -> size }
        .sum()

fun findSmallestDirSizeToDelete(inputPath: String): Int {
    val dirToSizes = calculateDirSizes(inputPath)
    val rootSize = dirToSizes["/"] ?: throw IllegalStateException("This should exist, but does not!")
    val unusedSpace = 70000000 - rootSize
    val requiredSpace = 30000000 - unusedSpace

    val filteredDirSizes =  dirToSizes
        .filter { (_, size) -> size >= requiredSpace }

    return filteredDirSizes
        .map { (_, size) -> size }
        .minOf { it }
}


private fun calculateDirSizes(inputPath: String): Map<String, Int> {
    val dirStack: ArrayDeque<String> = ArrayDeque()
    val dirToCalculatedSize: MutableMap<String, Int> = mutableMapOf()
    val dirNameToOccurrences: MutableMap<String, Int> = mutableMapOf()

    utils.readFile(inputPath)
        .split("\n")
        .forEach {
            when {
                it == "$ cd .." -> dirStack.pop()
                it.contains("$ cd") -> {
                    val dir = it.drop(5)
                    val numOccurrences = dirNameToOccurrences[dir] ?: 0

                    val mutatedDir = if (numOccurrences == 0) {
                        dir
                    } else {
                        dir + numOccurrences
                    }

                    dirNameToOccurrences[dir] = numOccurrences + 1

                    dirStack.push(mutatedDir)
                }
                it[0].isDigit() -> {
                    val (sizeStr, _) = it.split(" ")
                    val size = sizeStr.toInt()
                    dirStack.forEach { dir ->
                        var prevSize = dirToCalculatedSize[dir]
                        if (prevSize == null) prevSize = 0
                        val newSize = prevSize + size
                        dirToCalculatedSize[dir] = newSize
                    }
                }
            }
        }

    return dirToCalculatedSize
}