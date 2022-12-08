package day08

fun externallyVisibleTreeCount(inputPath: String): Int {
    val treeGrid = createTreeGrid(inputPath)
    val gridSize = treeGrid.size

    setVisible(treeGrid.rowsForwards()) { it.visibleFromLeft = true }
    setVisible(treeGrid.rowsBackwards()) { it.visibleFromRight = true }
    setVisible(treeGrid.columnsDown()) { it.visibleFromTop = true }
    setVisible(treeGrid.columnsUp()) { it.visibleFromBottom = true }

    val visible = treeGrid.flatten()
        .filter { it.visible(gridSize) }

    return visible.count()
}

fun largestScenicScore(inputPath: String): Int {
    val treeGrid = createTreeGrid(inputPath)

    setScenicScore(treeGrid.rowsForwards(),
        { tree, other -> tree.colIdx < other.colIdx },
        { tree, score -> tree.scenicScoreFromLeft = score })
    setScenicScore(treeGrid.rowsBackwards(),
        { tree, other -> tree.colIdx > other.colIdx },
        { tree, score -> tree.scenicScoreFromRight = score })
    setScenicScore(treeGrid.columnsDown(),
        { tree, other -> tree.rowIdx < other.rowIdx },
        { tree, score -> tree.scenicScoreFromTop = score })
    setScenicScore(treeGrid.columnsUp(),
        { tree, other -> tree.rowIdx > other.rowIdx },
        { tree, score -> tree.scenicScoreFromBottom = score })

    val trees = treeGrid.flatten()

    return trees.maxOf { it.calculateScenicScore() }
}

fun setVisible(treeViews: List<List<Tree>>, scoreSetter: (Tree) -> Unit) =
    treeViews.map { it.findVisibleTrees() }.flatten().forEach { scoreSetter(it) }

fun setScenicScore(treeViews: List<List<Tree>>, treeViewConstraint: (Tree, Tree) -> Boolean, scoreSetter: (Tree, Int) -> Unit) =
    treeViews.forEach { treeView ->
        treeView.forEach { tree ->
            val remainingTrees = treeView.filter { other -> treeViewConstraint(tree, other) }
            val viewDepth = when (val viewDepthIndex = remainingTrees.indexOfFirst { other -> tree.height <= other.height }) {
                -1 -> remainingTrees.size
                else -> viewDepthIndex + 1
            }
//                .count { other -> tree.height > other.height }

            scoreSetter(
                tree,
                viewDepth
            )
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
        .mapIndexed { rowIdx, row ->
            row.mapIndexed { colIdx, height ->
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

private fun Tree.calculateScenicScore(): Int =
    scenicScoreFromLeft * scenicScoreFromRight * scenicScoreFromBottom * scenicScoreFromTop

data class Tree(
    val rowIdx: Int,
    val colIdx: Int,
    val height: Int,
    var scenicScoreFromLeft: Int = 0,
    var scenicScoreFromRight: Int = 0,
    var scenicScoreFromTop: Int = 0,
    var scenicScoreFromBottom: Int = 0,
    var scenicScore: Int = 0,
    var visibleFromLeft: Boolean = false,
    var visibleFromRight: Boolean = false,
    var visibleFromTop: Boolean = false,
    var visibleFromBottom: Boolean = false,
)
