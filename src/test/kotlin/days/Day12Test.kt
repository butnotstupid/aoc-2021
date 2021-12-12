package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day12Test {

    private val dayTwelve = Day12()

    @Test
    fun testPartOne() {
        assertThat(dayTwelve.partOne(), `is`(4549))
        println("Part one answer: ${dayTwelve.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwelve.partTwo(), `is`(120535))
        println("Part two answer: ${dayTwelve.partTwo()}")
    }
}
