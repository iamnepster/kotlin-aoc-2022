import java.io.File

fun main() {
    val testData = File("src/main/resources/Day05-Test.txt").readText().lines()
    val data = File("src/main/resources/Day05.txt").readText().lines()

    fun solvePartOne(data: List<String>): String {
        return TODO()
    }

    fun createStack(data: List<String>): List<String> {
        val stacks = data
            .find { it.contains(Regex("^[0-9 ]*$")) }
            ?.split(" ")
            ?.last()
            ?.toInt()
        println(stacks)
        return data
            .filter { it.isNotBlank() && !it.contains(Regex("^.*[0-9].*$")) }
            .map { it[1].toString() }
            .filter { it.isNotBlank() }
    }
    println(createStack(testData))

    check(solvePartOne(testData) == "CMZ")
}