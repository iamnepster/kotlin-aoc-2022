import java.io.File

fun Char.toScore(): Int {
    return if (this.isUpperCase()) this - 'A' + 27 else this - 'a' + 1
}

fun partOne(data: String): Int = data.lines()
    .map { it.substring(0 until it.length / 2) to it.substring(it.length / 2) }
    .flatMap { it.first.toSet() intersect it.second.toSet() }
    .sumOf { it.toScore() }

fun partTwo(data: String): Int = data.lines()
    .chunked(3)
    .map { elfGroup ->
        elfGroup.zipWithNext()
            .map { (first, second) -> first.toSet() intersect second.toSet() }
    }
    .flatMap { it[0] intersect it[1] }
    .sumOf { it.toScore() }

fun main() {
    val testData = File("src/Day03-Test.txt").readText()
    val data = File("src/Day03.txt").readText()

    check(partOne(testData) == 157)
    println(partOne(data))

    check(partTwo(testData) == 70)
    println(partTwo(data))
}