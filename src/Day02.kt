fun main() {
    fun part1(input: List<String>): Int {
        val maxRed = 12
        val maxGreen = 13
        val maxBlue = 14

        var result = 0

        for (i in input.indices) {
            val line = input[i]

            var valid = true
            val drawings = line.replace("Game \\d+: ".toRegex(), "").split(";")
            drawings.forEach { draw ->
                draw.split(",").forEach { cubes ->
                    var red = 0
                    var green = 0
                    var blue = 0
                    val tokens = cubes.trim().split(" ")
                    if (tokens[1] == "red") {
                        red += tokens[0].toInt()
                    } else if (tokens[1] == "green") {
                        green += tokens[0].toInt()
                    } else if (tokens[1] == "blue") {
                        blue += tokens[0].toInt()
                    }
                    if (red > maxRed || green > maxGreen || blue > maxBlue) {
                        valid = false
                    }
                }
            }
            if (valid) {
                result += i + 1
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0

        for (i in input.indices) {
            val line = input[i]

            var minRed = 0
            var minGreen = 0
            var minBlue = 0

            val drawings = line.replace("Game \\d+: ".toRegex(), "").split(";")
            drawings.forEach { draw ->
                draw.split(",").forEach { cubes ->
                    val tokens = cubes.trim().split(" ")
                    val value = tokens[0].toInt()
                    if (tokens[1] == "red" && value > minRed) {
                        minRed = value
                    } else if (tokens[1] == "green" && value > minGreen) {
                        minGreen = value
                    } else if (tokens[1] == "blue" && value > minBlue) {
                        minBlue = value
                    }
                }
            }
            result += minRed * minGreen * minBlue
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testResult1 = part1(readInput("Day02_test"))
    testResult1.println()
    check(testResult1 == 8)


    val input = readInput("Day02")
    part1(input).println()

    val testResult2 = part2(readInput("Day02_test"))
    testResult2.println()
    check(testResult2 == 2286)

    part2(input).println()
}
