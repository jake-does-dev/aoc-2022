package day04

import utils.readFile

fun countOverlappingRanges(inputPath: String) : Int =
    readAsRangePair(inputPath)
        .count { (it.first overlaps it.second) or (it.second overlaps it.first) }

fun countOverlappingSegments(inputPath: String) : Int =
    readAsRangePair(inputPath)
        .count { it.first intersects it.second }

private fun readAsRangePair(inputPath: String) : List<Pair<Range, Range>> =
    readFile(inputPath)
        .split("\n")
        .map { it.split(",") }
        .map { Pair(it[0].asRange(), it[1].asRange()) }

private fun String.asRange() : Range {
    val rangeParts = split("-")
    return Range(rangeParts[0].toInt(), rangeParts[1].toInt())
}

private infix fun Range.overlaps(other: Range) : Boolean =
    start <= other.start && end >= other.end

private infix fun Range.intersects(other: Range) : Boolean =
    ((start..end) intersect (other.start..other.end)).isNotEmpty()

private data class Range(val start: Int, val end: Int)