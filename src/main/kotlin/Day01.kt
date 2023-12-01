class Day01 {
    val part1TestExpected = 142L
    val part2TestExpected = 281L
    fun part1(input: List<String>): Long {
        return input.sumOf { "${it.first { it.isDigit() }}${it.last { it.isDigit() }}".toLong() }
    }

    fun part2(input: List<String>): Long {
        val map = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

        fun Int.noNeg() = if (this < 0) Int.MAX_VALUE else this
        return input.sumOf { ln ->
            val points = ln.mapIndexed { index, c -> if (c.isDigit()) index to c.digitToInt() else Int.MAX_VALUE to -1 }
                .filter { it.first < ln.length } +
                    map.entries.map { ln.indexOf(it.key).noNeg() to it.value }.filter { it.first < ln.length } +
                    map.entries.map { ln.lastIndexOf(it.key).noNeg() to it.value }.filter { it.first < ln.length }
            points.minBy { it.first }.second * 10L + points.maxBy { it.first }.second
        }
    }
}