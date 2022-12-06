package day06

fun findStartOf(inputPath: String, packet: Packet) : List<Int> =
    utils.readFile(inputPath)
        .split("\n")
        .map { it.findStartOf(packet) }

private fun String.findStartOf(packet: Packet) : Int {
    val packetSize = packet.size
    return packetSize + windowed(packetSize).indexOfFirst { it.toSet().size == packetSize }
}

enum class Packet(val size: Int) {
    SIGNAL(4),
    MESSAGE(14);
}