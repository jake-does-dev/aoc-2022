package day04

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CampCleanupKtTest {
    @Test
    fun countOverlappingRangesExample() {
        assertEquals(2, countOverlappingRanges("src/test/resources/day04-example.txt"))
    }

    @Test
    fun countOverlappingRangesPuzzle() {
        assertEquals(513, countOverlappingRanges("src/test/resources/day04-puzzle.txt"))
    }

    @Test
    fun countOverlappingSegmentsExample() {
        assertEquals(4, countOverlappingSegments("src/test/resources/day04-example.txt"))
    }

    @Test
    fun countOverlappingSegmentsPuzzle() {
        assertEquals(878, countOverlappingSegments("src/test/resources/day04-puzzle.txt"))
    }
}