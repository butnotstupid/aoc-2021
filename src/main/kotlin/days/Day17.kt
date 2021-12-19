package days

class Day17 : Day(17) {

    override fun partOne(): Any {
        val (minX, maxX, minY, maxY) =
            inputList.first().let {
                Regex("""target area: x=(-?\d+)..(-?\d+), y=(-?\d+)..(-?\d+)""")
                    .find(it)!!.groupValues.drop(1).map { it.toInt() }
            }

        return reachedYs(minX, maxX, minY, maxY).maxOrNull()!!
    }

    override fun partTwo(): Any {
        val (minX, maxX, minY, maxY) =
            inputList.first().let {
                Regex("""target area: x=(-?\d+)..(-?\d+), y=(-?\d+)..(-?\d+)""")
                    .find(it)!!.groupValues.drop(1).map { it.toInt() }
            }

        return reachedYs(minX, maxX, minY, maxY).size
    }

    private fun reachedYs(minX: Int, maxX: Int, minY: Int, maxY: Int) =
        (0..2 * maxX).flatMap { x ->
            (minY..maxX).mapNotNull { y ->
                var (cx, cy) = x to y
                var (posx, posy) = 0 to 0
                var topY = Int.MIN_VALUE
                var reached = false

                while (posy >= minY) {
                    posx += cx
                    posy += cy
                    cx += stepToZero(cx)
                    cy -= 1

                    if (posy > topY) topY = posy
                    if (posx in minX..maxX && posy in minY..maxY) {
                        reached = true
                        break
                    }
                }

                if (reached) topY else null
            }
        }

    private fun stepToZero(x: Int) = when {
        x < 0 -> 1
        x == 0 -> 0
        x > 0 -> -1
        else -> throw IllegalArgumentException("Unexpected x=$x")
    }
}
