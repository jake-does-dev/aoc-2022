package day07

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class NoSpaceLeftOnDeviceKtTest {
    @Test
    fun findSmallSizeDirsExample() {
        assertEquals(95437, findSmallerSizeDirectorySum("src/test/resources/day07-example.txt"))
    }

    @Test
    fun findSmallSizeDirsPuzzle() {
        assertEquals(123456789, findSmallerSizeDirectorySum("src/test/resources/day07-puzzle.txt"))
    }

    @Test
    fun findSmallSizeDirs_DeeplyNested() {
        assertEquals(540000, findSmallerSizeDirectorySum("src/test/resources/day07-deeply-nested.txt"))
    }

    @Test
    fun findSmallSizeDirs_DeeplyNestedInTwoDirections() {
        assertEquals(1080000, findSmallerSizeDirectorySum("src/test/resources/day07-deeply-nested-two-directions.txt"))
    }
}