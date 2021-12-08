package days

class Day8 : Day(8) {

    override fun partOne(): Any {
        val uniqueDigitsLength = setOf(2, 3, 4, 7)
        return inputList.sumOf { input ->
            val (_, display) = input.split(" | ")
            display.split(" ").count { it.length in uniqueDigitsLength }
        }
    }

    override fun partTwo(): Any {
//        "9" is the only (6) that's single signal extra of 4+7
//        of remaining two (6) that contains 7 is "0", doesn't contain 7 is "6"
//        "3" is the only (5) containing 7
//        "5" is (5) with single signal to 6, another (5) is "2"

        return inputList.sumOf { input ->
            val (digits, display) = input.split(" | ")

            val digitByLen = digits.split(" ").map { it.toSet() }.groupBy { it.size }
            val (one, seven) = digitByLen.getValue(2).first() to digitByLen.getValue(3).first()
            val (four, eight) = digitByLen.getValue(4).first() to digitByLen.getValue(7).first()

            val nine = digitByLen.getValue(6).find { it.subtract(four.union(seven)).size == 1 }
            val (zero, six) = digitByLen.getValue(6).filter { it != nine }
                .partition { it.containsAll(seven) }
                .let { it.first.first() to it.second.first() }
            val three = digitByLen.getValue(5).find { it.containsAll(seven) }
            val (five, two) = digitByLen.getValue(5).filter { it != three}
                .partition { six.minus(it).size == 1 }
                .let { it.first.first() to it.second.first() }

            val digitMap = mapOf(
                zero to 0, one to 1, two to 2, three to 3, four to 4,
                five to 5, six to 6, seven to 7, eight to 8, nine to 9
            )

            display.split(" ").map { it.toSet() }
                .fold(0L) { sum, digit -> sum * 10 + digitMap.getValue(digit) }
        }
    }
}
