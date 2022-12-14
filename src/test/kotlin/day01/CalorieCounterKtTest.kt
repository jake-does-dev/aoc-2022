package day01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class CalorieCounterKtTest {
    @Test
    fun maxCaloriesExample() {
        assertEquals(24000, sumTopNCalories("src/test/resources/day01-example.txt"))
    }

    @Test
    fun maxCaloriesPuzzle() {
        assertEquals(69289, sumTopNCalories("src/test/resources/day01-puzzle.txt"))
    }

    @Test
    fun topNCaloriesSumExample() {
        assertEquals(45000, sumTopNCalories("src/test/resources/day01-example.txt", topN = 3))
    }

    @Test
    fun topNCaloriesSumPuzzle() {
        assertEquals(205615, sumTopNCalories("src/test/resources/day01-puzzle.txt", topN = 3))
    }
}