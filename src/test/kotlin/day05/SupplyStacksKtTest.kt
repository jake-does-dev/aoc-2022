package day05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class SupplyStacksKtTest {
    @Test
    fun moveStacksExample() {
        assertEquals("CMZ", moveStacks("src/test/resources/day05-example.txt", Ordering.REVERSED, SupplyStacksCreator.createExampleStacks()))
    }

    @Test
    fun moveStacksPuzzle() {
        assertEquals("FJSRQCFTN", moveStacks("src/test/resources/day05-puzzle.txt", Ordering.REVERSED, SupplyStacksCreator.createPuzzleStacks()))
    }

    @Test
    fun moveStacksPreserveOrderingExample() {
        assertEquals("MCD", moveStacks("src/test/resources/day05-example.txt", Ordering.SAME, SupplyStacksCreator.createExampleStacks()))
    }

    @Test
    fun moveStacksPreserveOrderingPuzzle() {
        assertEquals("CJVLJQPHS", moveStacks("src/test/resources/day05-puzzle.txt", Ordering.SAME,SupplyStacksCreator.createPuzzleStacks()))
    }
}