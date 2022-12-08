package day08

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TreetopTreeHouseKtTest {
    @Test
    fun visibleTreesExample() {
        assertEquals(21, externallyVisibleTreeCount("src/test/resources/day08-example.txt"))
    }

    @Test
    fun visibleTreesPuzzle() {
        assertEquals(1840, externallyVisibleTreeCount("src/test/resources/day08-puzzle.txt"))
    }

    @Test
    fun scenicScoreExample() {
        assertEquals(8, largestScenicScore("src/test/resources/day08-example.txt"))
    }

    @Test
    fun scenicScorePuzzle() {
        assertEquals(405769, largestScenicScore("src/test/resources/day08-puzzle.txt"))
    }
}