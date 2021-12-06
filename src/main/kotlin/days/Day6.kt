package days

class Day6 : Day(6) {

    override fun partOne(): Any {
        val initState = inputList[0].split(",").map { it.toInt() }
        return simulate(initState, 80)
    }

    override fun partTwo(): Any {
        val initState = inputList[0].split(",").map { it.toInt() }
        return simulate(initState, 256)
    }

    private fun simulate(initState: List<Int>, days: Int): Long {
        var current = Array(9) { 0L }
        for (number in initState) current[number] = current[number] + 1

        repeat(days) {
            val next = Array(9) { 0L }
            for (i in 1..8) next[i-1] = current[i]
            next[8] = current[0]  // newborns
            next[6] += current[0]  // + parents refreshed
            current = next
        }

        return current.sum()
    }
}
