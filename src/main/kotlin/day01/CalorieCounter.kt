package day01

import utils.readFile

fun sortedCalories(inputPath: String): List<Int> {
    val foodCalories = readFile(inputPath)
        .split("\n\n")

    val caloricSums = foodCalories
        .map { it.split("\n") }
        .map { list -> list.map { it.toInt() } }
        .map { it.sum() }

    return caloricSums.sortedDescending()
}

fun sumTopNCalories(inputPath: String, topN: Int = 1): Int {
    val sortedCalories = sortedCalories(inputPath)
    return sortedCalories.subList(0, topN).sum()
}
