package day07

import java.lang.IllegalStateException

fun findSmallerSizeDirectorySum(inputPath: String): Int {
    val root = createDirTree(inputPath)

    return calculateSizes(root)
        .filter { (_, size) -> size <= 100000 }
        .map { (_, size) -> size }
        .sum()
}

private fun createDirTree(inputPath: String): Directory {
    val lines = utils.readFile(inputPath).split("\n")

    var currentDir = Directory(parent = null, name = "/")
    var root = Directory(parent = null, name = "/")

    lines.forEach {
        when {
            "$ cd /" == it -> root = currentDir
            "$ cd .." in it -> currentDir = currentDir.moveOutOf()
            "$ cd" in it -> currentDir = currentDir.moveInTo(it)
            "$ ls" in it -> println("Listing contents, no action needs to be taken")
            "dir" in it -> currentDir = currentDir.addDirectory(it)
            else -> currentDir = currentDir.addFile(it)
        }
    }

    return root
}

private fun calculateSizes(root: Directory) : Map<String, Int> {
    val dirToSize = mutableMapOf<String, Int>()

    calculateSizes(root, dirToSize)

    println("hi")

    return dirToSize
}

private fun calculateSizes(currentDir: Directory, dirToSize: MutableMap<String, Int>) {
    val previous = dirToSize[currentDir.name]
    if (previous == null) {
        dirToSize[currentDir.name] = currentDir.size()
    } else {
        dirToSize[currentDir.name] = previous + currentDir.size()
    }

    val otherDirs = currentDir.nameToDirectory.values
    otherDirs.forEach { directory ->
        calculateSizes(directory, dirToSize)
    }
}

private fun Directory.size() : Int {
    return files.sumOf { it.size } +
            nameToDirectory.values.sumOf { it.size() }
}

private fun Directory.moveOutOf(): Directory =
    parent ?: throw IllegalStateException("Directory $name has no parent!")

private fun Directory.moveInTo(moveCommand: String): Directory =
    Directory(
        parent = this,
        name = Matcher.MOVE.findSingleMatch(moveCommand)
    )

private fun Directory.addDirectory(dirCommand: String): Directory {
    val dirName = Matcher.DIR.findSingleMatch(dirCommand)
    nameToDirectory[dirName] = Directory(parent = this, name = dirName)

    propagateChanges()
    return this
}

private fun Directory.addFile(fileDetails: String): Directory {
    val (size, name) = Matcher.FILE_DETAILS.findMatches(fileDetails, 2)
    files.add(File(name, size.toInt()))

    propagateChanges()
    return this
}

private fun Directory.propagateChanges() {
    var dir = this
    var parent = dir.parent
    while (parent != null) {
        parent.nameToDirectory[dir.name] = dir

        dir = parent
        parent = dir.parent
    }
}

data class Directory(
    var parent: Directory?,
    val name: String = "",
    val files: MutableList<File> = mutableListOf(),
    val nameToDirectory: MutableMap<String, Directory> = mutableMapOf()
) {
    override fun toString(): String {
        return "Directory(name = $name, parent = $parent)"
    }
}

data class File(
    val name: String,
    val size: Int
)

enum class Matcher(
    val regex: Regex
) {
    MOVE(Regex("""\$ cd (.+)""")),
    DIR(Regex("""dir (.+)""")),
    FILE_DETAILS(Regex("""(\d+) (.+)"""))
}

private fun Matcher.findMatches(value: String, numGroups: Int) : List<String> =
    regex.matchEntire(value)
        ?.groupValues
        ?.subList(1, numGroups + 1)
        ?: throw IllegalStateException("Expected regex match on the move command $name, but could not find a match!")

private fun Matcher.findSingleMatch(value: String) : String =
    findMatches(value, 1).first()