package days

class Day5 : Day(5) {

    override fun partOne(): Any {
        val regex = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()
        val lines = inputList.map {
            regex.find(it)!!.groupValues.drop(1).map { it.toInt() }
                .let { (x1, y1, x2, y2) -> Line(x1, y1, x2, y2) }
        }

        val count = Array(1000) { Array(1000) { 0 } }
        var overlaps = 0
        for (line in lines) {
            if (line.x1 != line.x2 && line.y1 != line.y2) continue

            for (x in line.x1 toward line.x2) {
                for (y in line.y1 toward line.y2) {
                    count[x][y] += 1
                    if (count[x][y] == 2) {
                        overlaps += 1
                    }
                }
            }
        }

        return overlaps
    }

    override fun partTwo(): Any {
        val regex = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()
        val lines = inputList.map {
            regex.find(it)!!.groupValues.drop(1).map { it.toInt() }
                .let { (x1, y1, x2, y2) -> Line(x1, y1, x2, y2) }
        }

        val count = Array(1000) { Array(1000) { 0 } }
        var overlaps = 0
        for (line in lines) {
            if (line.x1 != line.x2 && line.y1 != line.y2) {
                // diagonal
                val dy = if (line.y2 - line.y1 > 0 ) 1 else -1
                var y = line.y1
                for (x in line.x1 toward line.x2) {
                    count[x][y] += 1
                    if (count[x][y] == 2) {
                        overlaps += 1
                    }
                    y += dy
                }
            } else {
                for (x in line.x1 toward line.x2) {
                    for (y in line.y1 toward line.y2) {
                        count[x][y] += 1
                        if (count[x][y] == 2) {
                            overlaps += 1
                        }
                    }
                }
            }
        }

        return overlaps
    }

    private infix fun Int.toward(to: Int): IntProgression {
        val step = if (this > to) -1 else 1
        return IntProgression.fromClosedRange(this, to, step)
    }

    data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
}
