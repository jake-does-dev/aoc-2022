package day06

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TuningTroubleKtTest {

    @Test
    fun findStartOfPacketMarkerExample() {
        assertEquals(listOf(7, 5, 6, 10, 11), findStartOf( "src/test/resources/day06-example.txt", Packet.SIGNAL))
    }

    @Test
    fun findStartOfPacketMarkerPuzzle() {
        assertEquals(listOf(1804), findStartOf("src/test/resources/day06-puzzle.txt", Packet.SIGNAL))
    }

    @Test
    fun findStartOfMessageMarkerExample() {
        assertEquals(listOf(19, 23, 23, 29, 26), findStartOf("src/test/resources/day06-example.txt", Packet.MESSAGE))
    }

    @Test
    fun findStartOfMessageMarkerPuzzle() {
        assertEquals(listOf(2508), findStartOf("src/test/resources/day06-puzzle.txt", Packet.MESSAGE))
    }

}