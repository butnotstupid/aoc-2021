package days

class Day20 : Day(20) {

    private val padx = 250
    private val pady = 250

    override fun partOne(): Any {
        return extendImage(inputList, 2)
    }

    override fun partTwo(): Any {
        return extendImage(inputList, 50)
    }

    private fun extendImage(inputList: List<String>, stepsNumber: Int): Int {
        val extendMap = inputList[0].map { it != '.' }.toTypedArray()
        var canvas = Array(padx * 2 + 10) { Array(pady * 2 + 10) { false } }
        inputList.drop(2).forEachIndexed { x, s ->
            s.forEachIndexed { y, c ->
                if (c == '#') canvas[padx + x][pady + y] = true
            }
        }

        repeat(stepsNumber) {
            canvas = nextStep(canvas, extendMap)
        }

        return canvas.drop(100).dropLast(100).sumOf { it.drop(100).dropLast(100).count { it } }
    }

    private fun nextStep(canvas: Array<Array<Boolean>>, extendMap: Array<Boolean>): Array<Array<Boolean>> {
        val di = listOf(-1, -1, -1, 0, 0, 0, 1, 1, 1)
        val dj = listOf(-1, 0, 1, -1, 0, 1, -1, 0, 1)
        val next = Array(canvas.size) { Array(canvas.first().size) { false } }
        for (x in -padx + 2..padx - 2) {
            for (y in -pady + 2..pady - 2) {
                val (cx, cy) = x + padx to y + pady
                val decimal = generateSequence(1) { it * 2 }.take(9).toList().reversed().zip(
                        di.zip(dj).map { (i, j) -> canvas[cx + i][cy + j] }
                ).sumOf { (pow2, digit) -> pow2 * if (digit) 1 else 0 }
                next[cx][cy] = extendMap[decimal]
            }
        }
        return next
    }
}
