package day10

fun signalStrength(inputPath: String): Int {
    val cycles = mutableListOf(Cycle(cycleNumber = 1, value = 1))

    utils.readFile(inputPath)
        .lines()
        .forEach { cycles.addAll(it.cyclesForStep(cycles.last())) }

    val targetCycles = cycles.filter { (it.cycleNumber + 20) % 40 == 0 }
    val strengths = targetCycles.map { it.cycleNumber * it.value }

    return strengths.sum()
}

fun spritePixels(inputPath: String) {
    val cycles = mutableListOf(Cycle(cycleNumber = 1, value = 1))

    utils.readFile(inputPath)
        .split("\n")
        .forEach { cycles.addAll(it.cyclesForStep(cycles.last())) }

    val pixels = mutableListOf<Char>()
    cycles.forEach { cycle -> pixels += calculatePixel(cycle) }

    val lines = pixels.chunked(40).dropLast(1)
    lines.forEach { line ->
        line.forEach { print(it) }
        println()
    }
}

private fun String.cyclesForStep(lastCycle: Cycle): List<Cycle> {
    val cyclesForStep = mutableListOf(Cycle(lastCycle.cycleNumber + 1, lastCycle.value))
    if (startsWith("addx")) {
        val shift = drop(5).toInt()
        cyclesForStep.add(Cycle(lastCycle.cycleNumber + 2, lastCycle.value + shift))
    }
    return cyclesForStep
}

private fun calculatePixel(cycle: Cycle): Char {
    val crtPosition = cycle.position()
    val spritePosition = (cycle.value - 1)..(cycle.value + 1)

    return if (crtPosition in spritePosition) {
        '#'
    } else {
        '.'
    }
}

private data class Cycle(
    val cycleNumber: Int,
    val value: Int
) {
    fun position(): Int = (cycleNumber - 1) % 40
}