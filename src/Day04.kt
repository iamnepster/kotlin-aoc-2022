import java.io.File

private fun String.asIntRange(): IntRange =
    substringBefore("-").toInt() .. substringAfter("-").toInt()

private fun String.asRanges(): Pair<IntRange, IntRange> =
    substringBefore(",").asIntRange() to substringAfter(",").asIntRange()

private infix fun IntRange.fullyOverlaps(other: IntRange): Boolean =
    first <= other.first && last >= other.last

private infix fun IntRange.overlaps(other: IntRange): Boolean =
    first <= other.last && other.first <= last

fun main() {
    val testData = File("src/Day04-Test.txt").readText().lines()
    val data = File("src/Day04.txt").readText().lines()

    fun solvePartOne(data: List<String>): Int = data
        .map { it.asRanges() }
        .count { it.first fullyOverlaps it.second || it.second fullyOverlaps it.first }

    fun solvePartTwo(data: List<String>): Int = data
        .map { it.asRanges() }
        .count { it.first overlaps it.second }

    check(solvePartOne(testData) == 2)
    println(solvePartOne(data))

    check(solvePartTwo(testData) == 4)
    println(solvePartTwo(data))
}
