package days

class Day14 : Day(14) {

    override fun partOne(): Any {
        val init = inputList[0]
        val to = inputList.drop(2).map { it.split(" -> ") }.associate { it[0] to it[1] }
        return (1..10).fold(init) { str, _ ->
            str[0] +
            str.zipWithNext().map { (c, next) ->
                to.getOrDefault(String(charArrayOf(c, next)), "") + next
            }.joinToString("")
        }
            .groupBy { it }.mapValues { (_, value) -> value.size }.entries.sortedBy { it.value }
            .let { it.last().value - it.first().value }
    }

    override fun partTwo(): Any {
        val init = inputList[0]
        val to = inputList.drop(2).map { it.split(" -> ") }.associate { (it[0][0] to it[0][1]) to it[1][0] }

        val charFreq = init.groupBy { it }.mapValues { (_, value) -> value.size.toLong() }.toMutableMap()
        val pairCount = init.zipWithNext().groupBy { it }.mapValues { (_, value) -> value.size.toLong() }
        (1..40).fold(pairCount) { pairs, _ ->
            val newPairs = mutableMapOf<Pair<Char, Char>, Long>()
            pairs.forEach { (pair, count) ->
                if (pair !in to.keys) {
                    newPairs[pair] = count + (newPairs[pair] ?: 0)
                } else {
                    val left = pair.first to to[pair]!!
                    val right = to[pair]!! to pair.second
                    newPairs[left] = count + (newPairs[left] ?: 0)
                    newPairs[right] = count + (newPairs[right] ?: 0)
                    charFreq[to[pair]!!] = count + (charFreq[to[pair]!!] ?: 0)
                }
            }
            newPairs
        }
        return charFreq.entries.sortedBy { it.value }
            .let { it.last().value - it.first().value }
    }
}
