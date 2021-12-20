package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day20Test {

    private val dayTwenty = Day20()

    @Test
    fun testPartOne() {
        val partOne = dayTwenty.partOne()
        assertThat(partOne, `is`(5479))
        println("Part one answer: $partOne")
    }

    @Test
    fun testPartTwo() {
        val partTwo = dayTwenty.partTwo()
        assertThat(partTwo, `is`(19012))
        println("Part two answer: $partTwo")
    }
}
