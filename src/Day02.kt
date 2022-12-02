import java.io.File
import java.lang.IllegalArgumentException

sealed class Roshambo {
    abstract fun match(opponent: Roshambo): Int
    abstract fun wantedOutcome(outcome: String): Roshambo

    object Rock : Roshambo() {
        override fun match(opponent: Roshambo): Int =
            when (opponent) {
                is Scissors -> 6 + 1
                is Rock -> 3 + 1
                is Paper -> 0 + 1
            }

        override fun wantedOutcome(outcome: String): Roshambo =
            when (outcome) {
                "X" -> Scissors
                "Y" -> Rock
                "Z" -> Paper
                else -> throw IllegalArgumentException("'$outcome' is not a valid outcome!")
            }
    }

    object Paper : Roshambo() {
        override fun match(opponent: Roshambo): Int =
            when (opponent) {
                is Rock -> 6 + 2
                is Paper -> 3 + 2
                is Scissors -> 0 + 2
            }

        override fun wantedOutcome(outcome: String): Roshambo =
            when (outcome) {
                "X" -> Rock
                "Y" -> Paper
                "Z" -> Scissors
                else -> throw IllegalArgumentException("'$outcome' is not a valid outcome!")
            }
    }

    object Scissors : Roshambo() {
        override fun match(opponent: Roshambo): Int =
           when (opponent) {
                is Paper -> 6 + 3
                is Scissors -> 3 + 3
                is Rock -> 0 + 3
            }

        override fun wantedOutcome(outcome: String): Roshambo =
            when (outcome) {
                "X" -> Paper
                "Y" -> Scissors
                "Z" -> Rock
                else -> throw IllegalArgumentException("'$outcome' is not a valid outcome!")
            }
    }
}

fun String.toRoshambo(): Roshambo =
    if (this == "A" || this == "X") Roshambo.Rock
    else if (this == "B" || this == "Y") Roshambo.Paper
    else if (this == "C" || this == "Z") Roshambo.Scissors
    else throw IllegalArgumentException("'$this' is Not a valid move!")

fun main() {
    val testData = File("src/Day02-Test.txt").readText()
    val data = File("src/Day02.txt").readText()

    fun partOne(data: String): Int =
        data.lines()
            .map { it.split(" ") }
            .map { it[1].toRoshambo().match(it[0].toRoshambo())}
            .sum()

    fun partTwo(data: String): Int =
        data.lines()
            .map { it.split(" ") }
            .map { it[0].toRoshambo().wantedOutcome(it[1]).match(it[0].toRoshambo()) }
            .sum()

    check(partOne(testData) == 15)
    println(partOne(data))

    check(partTwo(testData) == 12)
    println(partTwo(data))
}