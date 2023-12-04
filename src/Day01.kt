import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        input.forEach { line ->
            var number = ""
            number += line.first { char ->
                try {
                    char.digitToInt()
                    true
                } catch (ignored: Exception) {
                    false
                }
            }
            number += line.last { char ->
                try {
                    char.digitToInt()
                    true
                } catch (ignored: Exception) {
                    false
                }
            }
            result += number.toInt()
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        val values = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        input.forEach { line ->
            var number = ""
            outer@ for (i in line.indices) {
                for (j in values.indices) {
                    if (line[i] == j.digitToChar() || line.substring(i, min(i + values[j].length, line.length - 1)) == values[j]
                    ) {
                        number += j
                        break@outer
                    }
                }
            }

            outer@ for (i in line.indices.reversed()) {
                for (j in values.indices) {
                    if (line[i] == j.digitToChar() || line.substring(max(i - values[j].length, 0) + 1, i + 1) == values[j]) {
                        number += j
                        break@outer
                    }
                }
            }
            result += number.toInt()
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testResult1 = part1(readInput("Day01_test"))
    testResult1.println()
    check(testResult1 == 142)

    val testResult2 = part2(readInput("Day01_test2"))
    testResult2.println()
    check(testResult2 == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
