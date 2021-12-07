package days

import kotlin.math.abs

class Day7 : Day(7) {

    override fun partOne(): Any {
        val positions = inputList.first().split(",").map { it.toInt() }
        val median = positions.sorted().elementAt(positions.size / 2)
        return positions.sumOf { abs(it - median) }
    }

    override fun partTwo(): Any {
        val positions = inputList.first().split(",").map { it.toInt() }
        return positions.distinct().minOf { point ->
            positions.sumOf {
                val steps = abs(it - point)
                (steps * (steps + 1)) / 2
            }
        }
    }
}
