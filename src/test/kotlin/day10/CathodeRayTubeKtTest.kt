package day10

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CathodeRayTubeKtTest {
    @Test
    fun signalStrengthExample() {
        assertEquals(13140, signalStrength("src/test/resources/day10-example.txt"))
    }

    @Test
    fun signalStrengthPuzzle() {
        assertEquals(15120, signalStrength("src/test/resources/day10-puzzle.txt"))
    }

    @Test
    fun spritePixelsExample() {
        spritePixels("src/test/resources/day10-example.txt")
    }

    @Test
    fun spritePixelsPuzzle() {
        // RKPJBPLA
        spritePixels("src/test/resources/day10-puzzle.txt")
    }
}