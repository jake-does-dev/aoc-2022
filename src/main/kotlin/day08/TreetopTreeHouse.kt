package day08

fun externallyVisibleTreeCount(inputPath: String): Int {
    val treeGrid = createTreeGrid(inputPath)

    setVisible(treeGrid.rowsForwards()) { it.visibleFromLeft = true }
    setVisible(treeGrid.rowsBackwards()) { it.visibleFromRight = true }
    setVisible(treeGrid.columnsDown()) { it.visibleFromTop = true }
    setVisible(treeGrid.columnsUp()) { it.visibleFromBottom = true }

    return treeGrid.flatten().count { it.visible(treeGrid.size) }
}

fun largestScenicScore(inputPath: String): Int {
    val treeGrid = createTreeGrid(inputPath)

    setScenicScoreWithConstraint(treeGrid.rowsForwards()) { tree, other -> tree.colIdx < other.colIdx }
    setScenicScoreWithConstraint(treeGrid.rowsBackwards()) { tree, other -> tree.colIdx > other.colIdx }
    setScenicScoreWithConstraint(treeGrid.columnsDown()) { tree, other -> tree.rowIdx < other.rowIdx }
    setScenicScoreWithConstraint(treeGrid.columnsUp()) { tree, other -> tree.rowIdx > other.rowIdx }

    return treeGrid
        .flatten()
        .maxOf { it.scenicScore }
}

fun setVisible(treeViews: List<List<Tree>>, visibleSetter: (Tree) -> Unit) =
    treeViews.map { it.findVisibleTrees() }.flatten().forEach { visibleSetter(it) }

fun setScenicScoreWithConstraint(treeViews: List<List<Tree>>, treeViewConstraint: (Tree, Tree) -> Boolean) =
    treeViews.forEach { treeView ->
        treeView.forEach { tree ->
            val remainingTrees = treeView.filter { other -> treeViewConstraint(tree, other) }
            val viewDepth = when (val viewDepthIndex = remainingTrees.indexOfFirst { other -> tree.height <= other.height }) {
                -1 -> remainingTrees.size
                else -> viewDepthIndex + 1
            }

            tree.scenicScore *= viewDepth
        }
    }

private fun List<Tree>.findVisibleTrees(): List<Tree> {
    val visibleTrees = mutableListOf<Tree>()

    // Outside tree is always visible
    visibleTrees += this[0]
    var tallestSeen = this[0].height

    this.drop(1).forEach {
        if (it.height > tallestSeen) {
            visibleTrees += it
            tallestSeen = it.height
        }
    }

    return visibleTrees
}

private fun List<List<Tree>>.rowsForwards(): List<List<Tree>> = (indices).map { rowIdx -> row(rowIdx) }
private fun List<List<Tree>>.rowsBackwards(): List<List<Tree>> = rowsForwards().map { it.reversed() }
private fun List<List<Tree>>.columnsDown(): List<List<Tree>> = (indices).map { colIdx -> column(colIdx) }
private fun List<List<Tree>>.columnsUp(): List<List<Tree>> = columnsDown().map { it.reversed() }
private fun List<List<Tree>>.column(colIdx: Int) = List(size) { this[it][colIdx] }
private fun List<List<Tree>>.row(rowIdx: Int) = List(size) { this[rowIdx][it] }

fun createTreeGrid(inputPath: String): List<List<Tree>> =
    utils.readFile(inputPath)
        .split("\n")
        .mapIndexed { rowIdx, line ->
            line.mapIndexed { colIdx, height ->
                Tree(
                    rowIdx,
                    colIdx,
                    height.toString().toInt()
                )
            }
        }


private fun Tree.visible(gridSize: Int): Boolean {
    val endPoints = setOf(0, gridSize - 1)
    val isExterior = ((rowIdx in endPoints) or (colIdx in endPoints))

    return if (isExterior) {
        true
    } else {
        visibleFromLeft or visibleFromRight or visibleFromTop or visibleFromBottom
    }
}

data class Tree(
    val rowIdx: Int,
    val colIdx: Int,
    val height: Int,
    var scenicScore: Int = 1,
    var visibleFromLeft: Boolean = false,
    var visibleFromRight: Boolean = false,
    var visibleFromTop: Boolean = false,
    var visibleFromBottom: Boolean = false,
)