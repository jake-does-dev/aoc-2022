package day09

import day09.Direction.*
import kotlin.math.abs

fun ropeTailCoverage(numberOfKnots: Int, inputPath: String): Int {
    val rope = (1..numberOfKnots).map { Knot(0, 0) }

    val tailCovered = mutableSetOf<Position>()

    utils.readFile(inputPath)
        .split("\n")
        .map { it.asMove() }
        .forEach {
            val moveTailCovered = it.applyMove(rope)
            tailCovered += moveTailCovered
        }

    return tailCovered.size
}

private fun String.asMove(): Move {
    val (dir, distance) = split(" ")

    return when (dir) {
        "U" -> Move(UP, distance.toInt())
        "R" -> Move(RIGHT, distance.toInt())
        "D" -> Move(DOWN, distance.toInt())
        "L" -> Move(LEFT, distance.toInt())
        else -> throw IllegalStateException("Unable to parse line $this into Move!")
    }
}

private fun Move.applyMove(rope: List<Knot>): Set<Position> {
    val head = rope.first()
    val tail = rope.last()
    val knotPairs = rope.subList(0, rope.size - 1).zip(rope.subList(1, rope.size))

    val moveTailCovered = mutableSetOf<Position>()

    println("Applying move: $direction $number")
    println("Head was at (${head.x}, ${head.y})")

    repeat(number) {
        head.move(direction)

        knotPairs.forEach { (front, back) -> front.pullKnot(back) }

        println("Tail has now covered (${tail.x}, ${tail.y})")
        moveTailCovered += Position(tail.x, tail.y)
    }

    println("Head is now at (${head.x}, ${head.y})")
    return moveTailCovered
}

private fun Knot.pullKnot(back: Knot) {

}

private fun Position.move(direction: Direction) {
    when(direction) {
        UP -> y += 1
        RIGHT -> x += 1
        DOWN -> y -= 1
        LEFT -> x -= 1
    }
}

data class Move(
    val direction: Direction,
    val number: Int
)

enum class Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;
}

class Knot(x: Int, y: Int) : Position(x, y)
open class Position(
    var x: Int,
    var y: Int
) {
    override fun toString(): String {
        return "Position(x=$x, y=$y)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}
