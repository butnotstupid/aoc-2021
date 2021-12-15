package days

import java.util.*

class Day15 : Day(15) {
    private val di = listOf(-1, 0, 0, 1)
    private val dj = listOf(0, -1, 1, 0)


    override fun partOne(): Any {
        val map = inputList.map { it.map { Character.getNumericValue(it) } }
        return findPath(map)
    }

    override fun partTwo(): Any {
        val map = inputList.map { it.map { Character.getNumericValue(it) } }
        val (n, m) = map.size to map.first().size
        val extended = Array(n * 5) { Array(m * 5) { 0 } }
        for (i in 0 until n * 5) {
            for (j in 0 until m * 5) {
                val distFromSource = i / n + j / m
                val (srcI, srcJ) = i % n to j % m
                extended[i][j] = 1 + (map[srcI][srcJ] + distFromSource - 1) % 9
            }
        }
        return findPath(extended.map { it.toList() })
    }

    private fun findPath(map: List<List<Int>>): Int {
        val queue = PriorityQueue<PathEntry>()
        val distTo = Array(map.size) { Array(map.first().size) { -1 } }

        val start = 0 to 0
        val end = map.size - 1 to map.first().size - 1
        queue.add(PathEntry(0, start.first, start.second))
        distTo[start.first][start.second] = 0
        while (queue.isNotEmpty()) {
            val (dist, i, j) = queue.poll()
            for ((dii, djj) in di.zip(dj)) {
                if (i + dii in map.indices && j + djj in map.first().indices) {
                    val prevDist = distTo[i + dii][j + djj]
                    if (prevDist == -1 || prevDist > dist + map[i + dii][j + djj]) {
                        if (prevDist != -1) {
                            queue.removeIf { it.i to it.j == i + dii to j + djj }
                        }
                        distTo[i + dii][j + djj] = dist + map[i + dii][j + djj]
                        queue.add(PathEntry(distTo[i + dii][j + djj], i + dii, j + djj))
                    }
                }
            }
        }

        return distTo[end.first][end.second]
    }


    data class PathEntry(val distance: Int, val i: Int, val j: Int): Comparable<PathEntry> {
        override fun compareTo(other: PathEntry): Int {
            return distance.compareTo(other.distance)
        }
    }
}
