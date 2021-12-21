package days

class Day21 : Day(21) {
    private val circleSize = 10

    override fun partOne(): Any {
        val maxScore = 1000
        val (startFirst, startSecond) = inputList.map { Regex("""Player \d+ starting position: (\d+)""").find(it)!!.groupValues[1].toInt() }

        val players = generateSequence(0) { 1 - it }
        val turns = generateSequence(1) { it + 1 }.chunked(3).zip(players)

        val endGame = turns.scan(Position(startFirst) to Position(startSecond)) { positions, turn ->
            val (first, second) = positions
            val (steps, player) = turn
            when (player) {
                0 -> Position(first.pos.step(steps), first.score + first.pos.step(steps), steps.last().toLong()) to second
                1 -> first to Position(second.pos.step(steps), second.score + second.pos.step(steps), steps.last().toLong())
                else -> throw IllegalArgumentException("Player $player unexpected")
            }
        }.first { it.first.score >= maxScore || it.second.score >= maxScore }

        return listOf(endGame.first, endGame.second).let { it.minOf { it.score } * it.maxOf { it.lastRolled } }
    }

    private fun Int.step(steps: List<Int>): Int {
        return 1 + (this + steps.sum() - 1) % circleSize
    }

    data class Position(val pos: Int, val score: Long = 0, val lastRolled: Long = 0)

    override fun partTwo(): Any {
        TODO()
    }
}
