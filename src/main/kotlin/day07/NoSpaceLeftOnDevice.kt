package day07

import utils.stack.pop
import utils.stack.push

fun findSmallerSizeDirectorySum(inputPath: String): Int =
    computeDirectories(inputPath)
        .filter { it.calculatedSize <= 100000 }
        .sumOf { it.calculatedSize }

fun findSmallestDirSizeToDelete(inputPath: String): Int {
    val directories = computeDirectories(inputPath)
    val rootSize = directories.first { it.name == "/" }.calculatedSize
    val unusedSpace = 70000000 - rootSize
    val requiredSpace = 30000000 - unusedSpace

    return directories
        .filter { it.calculatedSize >= requiredSpace }
        .minOf { it.calculatedSize }
}

private fun computeDirectories(inputPath: String): List<Directory> {
    val directoryStack: ArrayDeque<Directory> = ArrayDeque()
    val directories: MutableList<Directory> = mutableListOf()

    utils.readFile(inputPath)
        .split("\n")
        .forEach {
            when {
                it == "$ cd .." ->
                    directories += directoryStack.pop()

                it.contains("$ cd") ->
                    directoryStack.push(Directory(name = it.drop(5)))

                it[0].isDigit() -> {
                    val (sizeStr, _) = it.split(" ")
                    val size = sizeStr.toInt()
                    directoryStack.forEach { dir -> dir.calculatedSize += size }
                }
            }
        }

    repeat(directoryStack.size) { directories += directoryStack.pop() }

    return directories
}

data class Directory(val name: String, var calculatedSize: Int = 0)