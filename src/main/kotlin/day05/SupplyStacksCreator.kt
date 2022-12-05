package day05

object SupplyStacksCreator {
    fun createExampleStacks(): List<ArrayDeque<Char>> =
        listOf(
            stack('Z', 'N'),
            stack('M', 'C', 'D'),
            stack('P')
        )

    fun createPuzzleStacks(): List<ArrayDeque<Char>> =
        listOf(
            stack('B', 'V', 'S', 'N', 'T', 'C', 'H', 'Q'),
            stack('W', 'D', 'B', 'G'),
            stack('F', 'W', 'R', 'T', 'S', 'Q', 'B'),
            stack('L', 'G', 'W', 'S', 'Z', 'J', 'D', 'N'),
            stack('M', 'P', 'D', 'V', 'F'),
            stack('F', 'W', 'J'),
            stack('L', 'N', 'Q', 'B', 'J', 'V'),
            stack('G', 'T', 'R', 'C', 'J', 'Q', 'S', 'N'),
            stack('J', 'S', 'Q', 'C', 'W', 'D', 'M'),
        )

    private fun stack(vararg elements: Char): ArrayDeque<Char> {
        val stack = ArrayDeque<Char>()
        elements.forEach { stack.addLast(it) }
        return stack
    }
}