package day02

import kotlin.test.Test
import kotlin.test.assertEquals

internal class RockPaperScissorsKtTest {
    @Test
    fun rockPaperScissorsWithShapeExample() {
        assertEquals(15, rockPaperScissorsWithShape("src/test/resources/day02-example.txt"))
    }

    @Test
    fun rockPaperScissorsWithShapePuzzle() {
        assertEquals(8933, rockPaperScissorsWithShape("src/test/resources/day02-puzzle.txt"))
    }

    @Test
    fun rockPaperScissorsWithOutcomeExample() {
        assertEquals(12, rockPaperScissorsWithOutcome("src/test/resources/day02-example.txt"))
    }

    @Test
    fun rockPaperScissorsWithOutcomePuzzle() {
        assertEquals(11998, rockPaperScissorsWithOutcome("src/test/resources/day02-puzzle.txt"))
    }
}