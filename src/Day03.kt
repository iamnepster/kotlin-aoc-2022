import java.io.File

fun main() {
    val testData = File("src/Day03-Test.txt").readText()
    val data = File("src/Day03.txt").readText()

    fun findSimilarChar(s1: String, s2: String): Char {
        s1.forEach { if (s2.contains(it)) return it }
        return ' '
    }

    fun findSimilarChar(s1: String, s2: String, s3: String): Char {
        if (s1.length < s2.length && s1.length < s3.length) {
            s1.forEach {
                if (s2.contains(it) && s3.contains(it)) return it
            }
        }
        if (s2.length < s1.length && s2.length < s3.length) {
            s2.forEach {
                if (s1.contains(it) && s3.contains(it)) return it
            }
        }
        s3.forEach {
            if (s1.contains(it) && s2.contains(it)) return it
        }
        return ' '
    }

    fun getPointsForChar(char: Char): Int {
        val charCode = char.toInt()
        return if (charCode in 65..90) charCode - 38
        else charCode - 96
    }

    fun partOne(data: String): Int = data.lines()
        .map { Pair(it.substring(0, it.length / 2), it.substring(it.length / 2)) }
        .map { findSimilarChar(it.first, it.second) }
        .sumOf { getPointsForChar(it) }

    println(testData.lines().windowed(3, 3))
    fun partTwo(data: String): Int = data.lines()
        .windowed(3, 3)
        .map { findSimilarChar(it[0],it[1],it[2]) }
        .sumOf { getPointsForChar(it) }

    check(partOne(testData) == 157)
    println(partOne(data))

    check(partTwo(testData) == 70)
    println(partTwo(data))
}