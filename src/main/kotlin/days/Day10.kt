package days

import java.util.*

class Day10 : Day(10) {
    private val closes = mapOf(
        '(' to ')', '[' to ']',
        '{' to '}', '<' to '>',
    )
    private val errorPoints = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    private val completionPoints = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

    override fun partOne(): Any {
        return inputList.sumOf { line ->
            val stack = Stack<Char>()
            var linePoints = 0
            for (c in line) {
                when (c) {
                    in closes.keys -> stack.push(c)
                    closes[stack.peek()] -> stack.pop()
                    else -> linePoints = errorPoints.getValue(c)
                }
                if (linePoints != 0) break
            }
            linePoints
        }
    }

    override fun partTwo(): Any {
        val totals = inputList.mapNotNull { line ->
            val stack = Stack<Char>()
            var validLine = true
            for (c in line) {
                when (c) {
                    in closes.keys -> stack.push(c)
                    closes[stack.peek()] -> stack.pop()
                    else -> validLine = false
                }
                if (!validLine) break
            }
            if (!validLine) null
            else {
                var linePoints = 0L
                for (c in stack.reversed()) {
                    linePoints = linePoints * 5 + completionPoints.getValue(closes.getValue(c))
                }
                linePoints
            }
        }.sorted()

        return totals[totals.size / 2]
    }
}
