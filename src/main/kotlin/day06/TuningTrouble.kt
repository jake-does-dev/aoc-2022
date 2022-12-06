package day06

import day06.Packet.MESSAGE
import day06.Packet.SIGNAL

fun findStartOf(inputPath: String, packet: Packet) : List<Int> =
    utils.readFile(inputPath)
        .split("\n")
        .map { it.findStartOf(packet) }

private fun String.findStartOf(packet: Packet) : Int {
    val windowSize = when (packet) {
        SIGNAL -> 4
        MESSAGE -> 14
    }

    val index = windowed(size = windowSize)
        .withIndex()
        .find { (_, packetData) -> packetData.toSet().size == windowSize }
        ?.index ?: throw IllegalStateException("No start of packet found in signal!")

    return index + windowSize
}

enum class Packet {
    SIGNAL,
    MESSAGE;
}