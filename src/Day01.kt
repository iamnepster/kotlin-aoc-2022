import java.io.File

fun main() {
    val testData = File("src/Day01-Test.txt").readText()
    val data = File("src/Day01.txt").readText()

    fun partOne(data: String): Int =
        data.split("\n\n")
            .map { elf ->
                elf.lines().sumOf { it.toInt() }
            }
            .maxOf { it }

    fun partTwo(data: String): Int =
        data.split("\n\n")
            .map { elf ->
                elf.lines().sumOf { it.toInt() }
            }
            .sortedDescending()
            .take(3)
            .sum()

    check(partOne(testData) == 24000)
    println(partOne(data))

    check(partTwo(testData) == 45000)
    println(partTwo(data))
}
