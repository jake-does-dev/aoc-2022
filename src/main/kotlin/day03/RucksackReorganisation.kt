package day03

import utils.readFile

val itemToPriority = ((('a'..'z') union ('A'..'Z')) zip ((1..26) union (27..52))).toMap()

fun sackSplitting(inputPath: String): Int =
    readLinesAsSequence(inputPath)
        .map { it.splitAtMidpoint() }
        .sumItemIntersectionPriorities()

fun findBadges(inputPath: String): Int =
    readLinesAsSequence(inputPath)
        .chunked(3)
        .sumItemIntersectionPriorities()

private fun readLinesAsSequence(inputPath: String): Sequence<String> =
    readFile(inputPath)
        .split("\n")
        .asSequence()

private fun String.splitAtMidpoint(): List<CharSequence> =
    listOf(subSequence(0, length / 2), subSequence(length / 2, length))

private fun Sequence<List<CharSequence>>.sumItemIntersectionPriorities(): Int =
    flatMap { it.findItemIntersection() }
        .map { itemToPriority[it] ?: 0 }
        .sum()

private fun List<CharSequence>.findItemIntersection(): Set<Char> =
    fold(get(0).items()) { items, next -> ((items intersect next.items()) as MutableSet<Char>) }

private fun CharSequence.items() =
    itemToCount().keys

private fun CharSequence.itemToCount(): Map<Char, Int> =
    groupingBy { it }.eachCount()