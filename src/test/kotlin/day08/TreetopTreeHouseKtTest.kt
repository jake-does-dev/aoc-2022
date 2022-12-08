package day08

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TreetopTreeHouseKtTest {
    @Test
    fun findSmallSizeDirsExample() {
        assertEquals(21, externallyVisibleTreeCount("src/test/resources/day08-example.txt"))
    }

    @Test
    fun findSmallSizeDirsPuzzle() {
        assertEquals(1517599, externallyVisibleTreeCount("src/test/resources/day08-puzzle.txt"))
    }
}