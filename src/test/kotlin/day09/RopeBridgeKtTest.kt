package day09

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class RopeBridgeKtTest {
    @Test
    fun ropeTailCoverageExample() {
        assertEquals(13, ropeTailCoverage(2, "src/test/resources/day09-example.txt"))
    }

    @Test
    fun ropeTailCoveragePuzzle() {
        assertEquals(6745, ropeTailCoverage(2, "src/test/resources/day09-puzzle.txt"))
    }

    @Test
    fun ropeTailMultipleKnotsExample() {
        assertEquals(1, ropeTailCoverage(10, "src/test/resources/day09-example.txt"))
    }

    @Test
    fun ropeTailMultipleKnotsPuzzle() {
        assertEquals(6745, ropeTailCoverage(10, "src/test/resources/day09-puzzle.txt"))
    }
}