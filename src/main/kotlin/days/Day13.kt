package days

class Day13 : Day(13) {

    override fun partOne(): Any {
        val delimiter = inputList.indexOfFirst { it.isEmpty() }
        return inputList.withIndex().partition { it.index < delimiter }.let { (coord, directions) ->
            val initialPoints = coord.map { it.value.split(",").map { it.toInt() } }.map { (x, y) -> x to y }.toSet()

            directions.drop(1).map {
                it.value.removePrefix("fold along ").split("=").let { (dir, num) -> dir to num.toInt() }
            }
                .take(1)
                .fold(initialPoints, ::foldXY)
        }.size
    }

    override fun partTwo(): Any {
        val delimiter = inputList.indexOfFirst { it.isEmpty() }
        inputList.withIndex().partition { it.index < delimiter }.let { (coord, directions) ->
            val initialPoints = coord.map { it.value.split(",").map { it.toInt() } }.map { (x, y) -> x to y }.toSet()

            directions.drop(1).map {
                it.value.removePrefix("fold along ").split("=").let { (dir, num) -> dir to num.toInt() }
            }
                .fold(initialPoints, ::foldXY)
                .let { points ->
                    val (lx, gx) = points.map { it.first }.let { it.minOrNull()!! to it.maxOrNull()!! }
                    val (ly, gy) = points.map { it.second }.let { it.minOrNull()!! to it.maxOrNull()!! }

                    for (y in ly..gy) {
                        for (x in lx..gx) {
                            print(if (x to y in points) "#" else ".")
                        }
                        println()
                    }
                }
        }

        return "EPUELPBR"
    }

    private fun foldXY(points: Set<Pair<Int, Int>>, direction: Pair<String, Int>): Set<Pair<Int, Int>> {
        val (dir, num) = direction
        return when (dir) {
            "x" -> {
                points.partition { (x, _) -> x < num }.let { (left, right) ->
                    left.union(right.map { (x, y) -> 2 * num - x to y })
                }
            }
            "y" -> {
                points.partition { (_, y) -> y < num }.let { (up, down) ->
                    up.union(down.map { (x, y) -> x to 2 * num - y })
                }
            }
            else -> {
                throw IllegalArgumentException("Fold direction $dir is unknown")
            }
        }
    }
}
