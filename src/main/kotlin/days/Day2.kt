package days

class Day2 : Day(2) {

    override fun partOne(): Any {
        return inputList.map { it.split(" ").let { it[0] to it[1].toLong() } }
            .fold(Position(0, 0)) { pos, (dir, steps) ->
                when (dir) {
                    "forward" -> Position(pos.x + steps, pos.depth)
                    "down" -> Position(pos.x, pos.depth + steps)
                    "up" -> Position(pos.x, pos.depth - steps)
                    else -> throw IllegalArgumentException("Unknown command")
                }
            }.let { it.x * it.depth }
    }

    override fun partTwo(): Any {
        return inputList.map { it.split(" ").let { it[0] to it[1].toLong() } }
            .fold(Position(0, 0, 0)) { pos, (dir, steps) ->
                when (dir) {
                    "forward" -> Position(pos.x + steps, pos.depth + steps * pos.aim, pos.aim)
                    "down" -> Position(pos.x, pos.depth, pos.aim + steps)
                    "up" -> Position(pos.x, pos.depth, pos.aim - steps)
                    else -> throw IllegalArgumentException("Unknown command")
                }
            }.let { it.x * it.depth }
    }

    data class Position(val x: Long, val depth: Long, val aim: Long = 0)
}
