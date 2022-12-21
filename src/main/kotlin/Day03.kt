import java.io.File

private fun Char.toScore(): Int {
    return if (this.isUpperCase()) this - 'A' + 27 else this - 'a' + 1
}

fun main() {
    val testData = File("src/main/resources/Day03-Test.txt").readText().lines()
    val data = File("src/main/resources/Day03.txt").readText().lines()

    fun partOne(data: List<String>): Int = data
        .map { it.substring(0 until it.length / 2) to it.substring(it.length / 2) }
        .flatMap { it.first.toSet() intersect it.second.toSet() }
        .sumOf { it.toScore() }

    fun partTwo(data: List<String>): Int = data
        .chunked(3)
        .map { elfGroup ->
            elfGroup.zipWithNext()
                .map { (first, second) -> first.toSet() intersect second.toSet() }
        }
        .flatMap { it[0] intersect it[1] }
        .sumOf { it.toScore() }

    check(partOne(testData) == 157)
    println(partOne(data))

    check(partTwo(testData) == 70)
    println(partTwo(data))
}