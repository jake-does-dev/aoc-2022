package day02

import day02.Outcome.*
import day02.Shape.*

fun rockPaperScissorsWithShape(inputPath: String): Int =
    utils.readFile(inputPath)
        .split("\n")
        .map { it.shapeToShape() }
        .sumOf { it.calculateScore() }

fun rockPaperScissorsWithOutcome(inputPath: String): Int =
    utils.readFile(inputPath)
        .split("\n")
        .map { it.shapeToOutcome() }
        .map { it.selectShapeFromOutcome() }
        .sumOf { it.calculateScore() }

private fun String.shapeToShape(): Pair<Shape, Shape> {
    val parts = split(" ")
    return shape(parts[0]) to shape(parts[1])
}

private fun Pair<Shape, Shape>.calculateScore(): Int {
    val (opponentShape, myShape) = this
    return myShape.selectionScore() + myShape.outcomeScore(opponentShape)
}

private fun String.shapeToOutcome(): Pair<Shape, Outcome> {
    val parts = split(" ")
    return shape(parts[0]) to outcome(parts[1])
}

private fun Pair<Shape, Outcome>.selectShapeFromOutcome(): Pair<Shape, Shape> {
    val (opponentShape, outcome) = this

    val myShape =
        when (outcome) {
            WIN -> when (opponentShape) {
                ROCK -> PAPER
                SCISSORS -> ROCK
                PAPER -> SCISSORS
            }

            DRAW -> opponentShape
            LOSE -> when (opponentShape) {
                ROCK -> SCISSORS
                SCISSORS -> PAPER
                PAPER -> ROCK
            }
        }

    return opponentShape to myShape
}

private fun Shape.selectionScore(): Int =
    when (this) {
        ROCK -> 1
        PAPER -> 2
        SCISSORS -> 3
    }

private fun Shape.outcomeScore(other: Shape): Int =
    when (this to other) {
        ROCK to SCISSORS, SCISSORS to PAPER, PAPER to ROCK -> 6
        ROCK to ROCK, SCISSORS to SCISSORS, PAPER to PAPER -> 3
        else -> 0
    }

private fun shape(representation: String): Shape =
    when (representation) {
        "A", "X" -> ROCK
        "B", "Y" -> PAPER
        "C", "Z" -> SCISSORS
        else -> throw IllegalStateException("The representation $representation has no associated shape!")
    }

private fun outcome(representation: String): Outcome =
    when (representation) {
        "X" -> LOSE
        "Y" -> DRAW
        "Z" -> WIN
        else -> throw IllegalArgumentException("The representation $representation has no associated outcome!")
    }

private enum class Shape {
    ROCK,
    PAPER,
    SCISSORS;
}

private enum class Outcome {
    WIN,
    DRAW,
    LOSE;
}
