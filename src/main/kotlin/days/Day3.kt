package days

import kotlin.math.pow

class Day3 : Day(3) {

    override fun partOne(): Any {
        val a = inputList.map { it.map { Character.getNumericValue(it) }.toTypedArray() }.toTypedArray()
        val bitLen = inputList.first().length
        val n = inputList.size
        var gammaRate = 0
        var epsilonRate = 0
        var twoPowered = 1

        for (bitNumber in bitLen-1 downTo 0) {
            var sum = 0
            for (i in a.indices) sum+=a[i][bitNumber]

            if (sum > n / 2) {
                gammaRate += twoPowered
            } else {
                epsilonRate += twoPowered
            }

            twoPowered *= 2
        }

        return gammaRate * epsilonRate
    }

    override fun partTwo(): Any {
        val a = inputList.map { it.map { Character.getNumericValue(it) }.toTypedArray() }.toTypedArray()
        val bitLen = inputList.first().length
        val n = inputList.size
        val inForOxygen = Array(n) { true }
        val inForCO = Array(n) { true }

        for (bitNumber in 0 until bitLen) {
            val filteredOxygen = a.filterIndexed { index, _ -> inForOxygen[index] }
            if (filteredOxygen.size > 1) {
                val countOxygen = filteredOxygen.count { it[bitNumber] == 1 }
                val toDiscard = if (2 * countOxygen >= filteredOxygen.size) 0 else 1
                for (i in a.indices) {
                    if (a[i][bitNumber] == toDiscard) inForOxygen[i] = false
                }
            }

            val filteredCO = a.filterIndexed { index, _ -> inForCO[index] }
            if (filteredCO.size > 1) {
                val countCO = filteredCO.count { it[bitNumber] == 1 }
                val toDiscard = if (2 * countCO >= filteredCO.size) 1 else 0
                for (i in a.indices) {
                    if (a[i][bitNumber] == toDiscard) inForCO[i] = false
                }
            }
        }

        val oxygenArray = inForOxygen.indexOf(true).let { a[it] }
        val coArray = inForCO.indexOf(true).let { a[it] }

        var oxygen = 0
        var co = 0
        var twoPowered = (2.0).pow(bitLen-1).toInt()
        for (i in 0 until bitLen) {
            oxygen += if (oxygenArray[i] == 1) twoPowered else 0
            co += if (coArray[i] == 1) twoPowered else 0
            twoPowered /= 2
        }

        return  oxygen * co
    }
}
