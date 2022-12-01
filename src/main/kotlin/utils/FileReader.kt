package utils

import java.io.File

fun readFile(path: String) : String {
    return File(path).readText()
}