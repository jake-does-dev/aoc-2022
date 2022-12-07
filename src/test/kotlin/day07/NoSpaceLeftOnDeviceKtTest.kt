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
        assertEquals(1517599, findSmallerSizeDirectorySum("src/test/resources/day07-puzzle.txt"))
    }

    @Test
    fun findSmallestDirToDeleteExample() {
        assertEquals(24933642, findSmallestDirSizeToDelete("src/test/resources/day07-example.txt"))
    }

    @Test
    fun findSmallestDirToDeletePuzzle() {
        assertEquals(2481982, findSmallestDirSizeToDelete("src/test/resources/day07-puzzle.txt"))
    }

    @Test
    fun findSmallSizeDirs_DeeplyNested() {
        assertEquals(540000, findSmallerSizeDirectorySum("src/test/resources/day07-deeply-nested.txt"))
    }

    @Test
    fun findSmallSizeDirs_DeeplyNestedInTwoDirections() {
        assertEquals(810000, findSmallerSizeDirectorySum("src/test/resources/day07-deeply-nested-two-directions.txt"))
    }

    @Test
    fun findSmallSizeDirs_DuplicateDirNames() {
        assertEquals(540000, findSmallerSizeDirectorySum("src/test/resources/day07-duplicate-dir-names.txt"))
    }
}