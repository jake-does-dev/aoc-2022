package day01

import kotlin.test.Test
import kotlin.test.assertEquals

internal class CalorieCounterTest {
    @Test
    fun maxCaloriesExample() {
        assertEquals(24000, sumTopNCalories("src/main/resources/day01/example.txt"))
    }

    @Test
    fun maxCaloriesPuzzle() {
        assertEquals(69289, sumTopNCalories("src/main/resources/day01/puzzle.txt"))
    }

    @Test
    fun topNCaloriesSumExample() {
        assertEquals(45000, sumTopNCalories("src/main/resources/day01/example.txt", topN = 3))
    }

    @Test
    fun topNCaloriesSumPuzzle() {
        assertEquals(205615, sumTopNCalories("src/main/resources/day01/puzzle.txt", topN = 3))
    }
}