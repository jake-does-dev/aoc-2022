package day03

import utils.readFile

fun sackSplitting(inputPath: String): Int {
    return readLinesAsSequence(inputPath)
        .map { it.splitting() }
        .sumItemIntersectionPriorities()
}

fun findBadges(inputPath: String): Int {
    return readLinesAsSequence(inputPath)
        .chunked(3)
        .sumItemIntersectionPriorities()
}

fun readLinesAsSequence(inputPath: String) : Sequence<String> =
    readFile(inputPath)
        .split("\n")
        .asSequence()

fun String.splitting(): List<CharSequence> {
    val midPoint = length / 2
    return listOf(subSequence(0, midPoint), subSequence(midPoint, length))
}

private fun Sequence<List<CharSequence>>.sumItemIntersectionPriorities(): Int {
    val itemToPriority = itemToPriority()

    return this
        .map { it.findItemIntersection() }
        .flatten()
        .map { itemToPriority[it] ?: 0 }
        .sum()
}

private fun List<CharSequence>.findItemIntersection(): Set<Char> {
    return fold(get(0).items()) { items, next -> ((items intersect next.items()) as MutableSet<Char>) }
}

private fun CharSequence.items() =
    this.itemToCount().keys

private fun CharSequence.itemToCount(): Map<Char, Int> {
    val itemToCount = HashMap<Char, Int>()
    this.forEach {
        val previous = itemToCount.getOrDefault(it, 0)
        itemToCount[it] = previous + 1
    }
    return itemToCount
}

private fun itemToPriority(): Map<Char, Int> {
    val itemToPriority = HashMap<Char, Int>()

    (('a'..'z') zip (1..26))
        .forEach { (item, priority) -> itemToPriority[item] = priority }
    (('A'..'Z') zip (27..52))
        .forEach { (item, priority) -> itemToPriority[item] = priority }

    return itemToPriority
}