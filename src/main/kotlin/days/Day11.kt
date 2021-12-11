package days

class Day11 : Day(11) {

    override fun partOne(): Any {
        val grid = inputList.map { it.map { Character.getNumericValue(it) }.toTypedArray() }.toTypedArray()
        return generateSequence { step(grid) }.take(100).sum()
    }

    override fun partTwo(): Any {
        val grid = inputList.map { it.map { Character.getNumericValue(it) }.toTypedArray() }.toTypedArray()
        return generateSequence { step(grid) }.indexOfFirst { it == 100 } + 1
    }

    private fun step(grid: Array<Array<Int>>): Int {
        // increase each octopus level +1
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                grid[i][j] += 1
            }
        }
        // flash all
        val flashed = Array(grid.size) { Array(grid.first().size) { false } }
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (!flashed[i][j] && grid[i][j] > 9) {
                    flash(i, j, grid, flashed)
                }
            }
        }
        // zero flashed
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] > 9) grid[i][j] = 0
            }
        }

        return flashed.sumOf { it.count { it } }
    }

    private fun flash(i: Int, j: Int, grid: Array<Array<Int>>, flashed: Array<Array<Boolean>>) {
        val di = listOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val dj = listOf(-1, 0, 1, -1, 1, -1, 0, 1)

        flashed[i][j] = true
        for ((dii, djj) in di.zip(dj)) {
            if (i + dii in grid.indices && j + djj in grid.first().indices) {
                grid[i + dii][j + djj] += 1
                if (grid[i + dii][j + djj] > 9 && !flashed[i + dii][j + djj]) {
                    flash(i + dii, j + djj, grid, flashed)
                }
            }
        }
    }
}
