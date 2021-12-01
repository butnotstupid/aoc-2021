package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        val depths = inputList.map { it.toLong() }
        return depths.zipWithNext().count { (a, next) -> next > a }
    }

    override fun partTwo(): Any {
        val depths = inputList.map { it.toLong() }.toLongArray()

        var count = 0
        for (i in depths.indices.drop(3)) {
            if (depths[i] > depths[i-3]) count++
        }

        return count
    }
}
