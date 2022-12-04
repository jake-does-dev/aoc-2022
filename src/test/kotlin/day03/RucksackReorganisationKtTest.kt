package day03

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RucksackReorganisationKtTest {
    @Test
    fun rucksackCommonItemsExample() {
        assertEquals(157, sackSplitting("src/test/resources/day03-example.txt"))
    }

    @Test
    fun rucksackCommonItemsPuzzle() {
        assertEquals(8394, sackSplitting("src/test/resources/day03-puzzle.txt"))
    }

    @Test
    fun rucksackFindBadgesExample() {
        assertEquals(70, findBadges("src/test/resources/day03-example.txt"))
    }

    @Test
    fun rucksackFindBadgesPuzzle() {
        assertEquals(2413, findBadges("src/test/resources/day03-puzzle.txt"))
    }
}