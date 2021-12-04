package days

class Day4 : Day(4) {

    override fun partOne(): Any {
        val numberSeq = inputList[0].split(",").map { it.toLong() }
        val boards = inputList.drop(1).chunked(6).map { block ->
            block.drop(1).map { line ->
                line.split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
            }.let { numbers ->
                Board(numbers)
            }
        }

        numberSeq.forEachIndexed { numberIndex, number ->
            boards.forEachIndexed { boardIndex, board ->
                board.mark(number)
                if (board.isWon()) {
                    println("Board #$boardIndex won on turn #$numberIndex = $number")
                    return board.winScore(number)
                }
            }
        }

        return "No answer found"
    }

    override fun partTwo(): Any {
        val numberSeq = inputList[0].split(",").map { it.toLong() }
        val boards = inputList.drop(1).chunked(6).map { block ->
            block.drop(1).map { line ->
                line.split(" ").filter { it.isNotEmpty() }.map { it.toLong() }
            }.let { numbers ->
                Board(numbers)
            }
        }

        var boardsLeft = boards.size
        numberSeq.forEachIndexed { numberIndex, number ->
            boards.forEachIndexed { boardIndex, board ->
                if (!board.isWon()){
                    board.mark(number)
                    if (board.isWon()) {
                        boardsLeft -= 1
                        if (boardsLeft == 0) {
                            println("Board #$boardIndex was last to win on turn #$numberIndex = $number")
                            return board.winScore(number)
                        }
                    }
                }
            }
        }

        return "No answer found"
    }

    data class Board(private val numbers: List<List<Long>>) {
        private val size = 5
        private val marked = Array(5) { Array(5) { false } }
        private var won = false

        fun mark(number: Long) {
            for (i in 0 until size) {
                for (j in 0 until size) {
                    if (numbers[i][j] == number) marked[i][j] = true
                }
            }
        }

        fun isWon(): Boolean {
            if (won) return won

            for (i in 0 until size) {
                var rowAll = true
                for (j in 0 until size) {
                    rowAll = rowAll && marked[i][j]
                }
                if (rowAll) {
                    won = true
                    return won
                }
            }

            for (j in 0 until size) {
                var columnAll = true
                for (i in 0 until size) {
                    columnAll = columnAll && marked[i][j]
                }
                if (columnAll) {
                    won = true
                    return won
                }
            }

            return won
        }

        fun winScore(lastCalled: Long): Long {
            var unmarkedSum = 0L
            for (i in 0 until size) {
                for (j in 0 until size) {
                    unmarkedSum += if (!marked[i][j]) numbers[i][j] else 0
                }
            }

            return lastCalled * unmarkedSum
        }
    }
}
