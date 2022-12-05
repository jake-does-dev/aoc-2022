package day05

import day05.Ordering.SAME
import day05.Ordering.REVERSED

fun moveStacks(inputFile: String, ordering: Ordering, stacks: List<ArrayDeque<Char>>): String {
    val moves = utils.readFile(inputFile)
        .split("\n")
        .map { it.parseMove() }

    moves.forEach { stacks.performMove(it, ordering) }

    return stacks.getMessage()
}

val moveRegex = Regex("""move (\d+) from (\d+) to (\d+)""")

private fun String.parseMove() : Move {
    val (number, fromStack, toStack) = moveRegex.matchEntire(this)
        ?.groupValues
        ?.subList(1, 4) ?: throw IllegalStateException("Unable to match regex on input $this")

    return Move(number = number.toInt(), fromIndex = fromStack.toInt() - 1, toIndex = toStack.toInt() - 1)
}

private fun List<ArrayDeque<Char>>.performMove(move: Move, ordering: Ordering) {
    val fromStack = get(move.fromIndex)
    val toStack = get(move.toIndex)

    when (ordering) {
        REVERSED -> repeat(move.number) { toStack.push(fromStack.pop()) }
        SAME -> {
            val intermediateStack = ArrayDeque<Char>()
            repeat(move.number) { intermediateStack.push(fromStack.pop()) }
            repeat(move.number) { toStack.push(intermediateStack.pop()) }
        }
    }
}

private fun List<ArrayDeque<Char>>.getMessage() : String = map { it.peek() }.joinToString("")

private fun <T> ArrayDeque<T>.push(element: T) : Unit = addLast(element)
private fun <T> ArrayDeque<T>.pop() : T = removeLast()
private fun <T> ArrayDeque<T>.peek() : T = last()

private data class Move(val number: Int, val fromIndex: Int, val toIndex: Int)
enum class Ordering {
    REVERSED,
    SAME;
}