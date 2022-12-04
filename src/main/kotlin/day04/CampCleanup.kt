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

private fun String.asRange() : Range =
    if (matches(Regex("""\d+-\d+"""))) {
        val rangeParts = split("-")
        Range(rangeParts[0].toInt(), rangeParts[1].toInt())
    } else {
        throw IllegalStateException("Cannot parse input! The regex does not match for the following: $this")
    }

private infix fun Range.overlaps(other: Range) : Boolean =
    start <= other.start && end >= other.end

private infix fun Range.intersects(other: Range) : Boolean =
    ((start..end) intersect (other.start..other.end)).isNotEmpty()

private data class Range(val start: Int, val end: Int)