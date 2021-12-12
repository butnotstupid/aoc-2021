package days

class Day12 : Day(12) {
    private val edges = inputList.map { it.split("-") }
        .flatMap { (from, to) -> listOf(from to to, to to from) }
        .groupBy { it.first }
        .mapValues { (_, value) -> value.map { (_, to) -> to } }

    override fun partOne(): Any {
        val visited = setOf("start")
        return countPaths("start", visited, edges, true)
    }

    override fun partTwo(): Any {
        val visited = setOf("start")
        return countPaths("start", visited, edges, false)
    }

    private fun countPaths(s: String, visited: Set<String>, edges: Map<String, List<String>>, beenTwice: Boolean): Int {
        if (s == "end") return 1
        return edges.getOrDefault(s, emptyList()).sumBy { next ->
            if (next !in visited || next.all { it.isUpperCase() }) countPaths(next, visited + next, edges, beenTwice)
            else if (next != "start" && !beenTwice) countPaths(next, visited + next, edges, true)
            else 0
        }
    }
}
