package day08

fun externallyVisibleTreeCount(inputPath: String): Int {
    val treeGrid = createTreeGrid(inputPath)

    val treeViews = mutableListOf<List<Tree>>()
    treeViews.addAll(treeGrid.rowsForwards())
    treeViews.addAll(treeGrid.rowsBackwards())
    treeViews.addAll(treeGrid.columnsDown())
    treeViews.addAll(treeGrid.columnsUp())

    val sum = treeViews
        .map { it.makeMonotonicallyIncreasing() }
        .flatten()
        .toSet()
        .sumOf { it.height }

    return sum
}

private fun List<Tree>.makeMonotonicallyIncreasing(): List<Tree> {
    val monotonicallyIncreasing = mutableListOf<Tree>()

    // Outside tree is always visible
    monotonicallyIncreasing.add(this[0])
    var tallestSeen = this[0].height

    this.drop(1).forEach {
        if (it.height > tallestSeen) {
            monotonicallyIncreasing.add(it)
            tallestSeen = it.height
        }
    }

    return monotonicallyIncreasing
}

private fun List<List<Tree>>.rowsForwards(): List<List<Tree>> = map { it }
private fun List<List<Tree>>.rowsBackwards(): List<List<Tree>> = map { it.reversed() }
private fun List<List<Tree>>.columnsDown(): List<List<Tree>> = columns((0 until this[0].size))
private fun List<List<Tree>>.columnsUp(): List<List<Tree>> = columns(this[0].size - 1 downTo 0)
private fun List<List<Tree>>.columns(rowRange: IntProgression): List<List<Tree>> {
    val treeColumns = mutableListOf<List<Tree>>()
    val gridSize = this[0].size

    (0 until gridSize).forEach { colIdx ->
        val treeColumn = mutableListOf<Tree>()
        rowRange.forEach { rowIdx ->
            treeColumn.add(this[rowIdx][colIdx])
        }

        treeColumns.add(treeColumn)
    }

    return treeColumns
}

fun createTreeGrid(inputPath: String): List<List<Tree>> =
    utils.readFile(inputPath)
        .split("\n")
        .map { it.map { height -> Tree(height.toString().toInt()) } }

fun main() {
    externallyVisibleTreeCount("src/test/resources/day08-example.txt")
}

data class Tree(val height: Int, var visible: Boolean = false)
