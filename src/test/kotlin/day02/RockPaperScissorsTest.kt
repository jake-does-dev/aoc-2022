package day02

import kotlin.test.Test
import kotlin.test.assertEquals

internal class RockPaperScissorsTest {
    @Test
    fun rockPaperScissorsWithShapeExample() {
        assertEquals(15, rockPaperScissorsWithShape("src/main/resources/day02/example.txt"))
    }

    @Test
    fun rockPaperScissorsWithShapePuzzle() {
        assertEquals(8933, rockPaperScissorsWithShape("src/main/resources/day02/puzzle.txt"))
    }

    @Test
    fun rockPaperScissorsWithOutcomeExample() {
        assertEquals(12, rockPaperScissorsWithOutcome("src/main/resources/day02/example.txt"))
    }

    @Test
    fun rockPaperScissorsWithOutcomePuzzle() {
        assertEquals(11998, rockPaperScissorsWithOutcome("src/main/resources/day02/puzzle.txt"))
    }
}