package days

import java.util.Stack

class Day9 : Day(9) {

    override fun partOne(): Any {
        val map = inputList.map { it.map { Character.getNumericValue(it) } }
        return map.flatMapIndexed { i, row ->
            row.mapIndexed { j, height ->
                var risk = height + 1
                if (i > 0 && map[i-1][j] <= height) risk = 0
                if (i < map.size-1 && map[i+1][j] <= height) risk = 0
                if (j > 0 && map[i][j-1] <= height) risk = 0
                if (j < map.first().size-1 && map[i][j+1] <= height) risk = 0
                risk
            }
        }.sum()
    }

    override fun partTwo(): Any {
        val map = inputList.map { it.map { Character.getNumericValue(it) } }
        val color = Array(map.size) { Array(map.first().size) { 0 } }
        var lastColor = 0
        for (i in map.indices) {
            for (j in map.first().indices) {
                if (map[i][j] != 9 && color[i][j] == 0) {
                    lastColor += 1
                    color[i][j] = lastColor
                    spreadColor(i, j, color, map)
                }
            }
        }

        return color.flatten().groupBy { it }.filterKeys { it != 0 }.values.map { it.size }
            .sortedDescending().take(3).reduce { acc, next -> acc * next }
    }

    private fun spreadColor(iStart: Int, jStart: Int, color: Array<Array<Int>>, map: List<List<Int>>) {
        val stack = Stack<Pair<Int, Int>>()
        stack.push(iStart to jStart)
        while (stack.isNotEmpty()) {
            val (i, j) = stack.pop()
            if (i > 0 && map[i-1][j] != 9 && color[i-1][j] == 0) {
                color[i-1][j] = color[i][j]
                stack.push(i-1 to j)
            }
            if (i < map.size-1 && map[i+1][j] != 9 && color[i+1][j] == 0) {
                color[i+1][j] = color[i][j]
                stack.push(i+1 to j)
            }
            if (j > 0 && map[i][j-1] != 9 && color[i][j-1] == 0) {
                color[i][j-1] = color[i][j]
                stack.push(i to j-1)
            }
            if (j < map.first().size-1 && map[i][j+1] != 9 && color[i][j+1] == 0) {
                color[i][j+1] = color[i][j]
                stack.push(i to j+1)
            }
        }
    }
}
