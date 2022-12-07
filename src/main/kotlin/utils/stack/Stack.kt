package utils.stack

fun <T> ArrayDeque<T>.push(element: T) : Unit = addLast(element)
fun <T> ArrayDeque<T>.pop() : T = removeLast()
fun <T> ArrayDeque<T>.peek() : T = last()