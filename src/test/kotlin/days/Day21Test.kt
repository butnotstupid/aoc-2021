package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day21Test {

    private val dayTwentyOne = Day21()

    @Test
    fun testPartOne() {
        assertThat(dayTwentyOne.partOne(), `is`(920580L))
        println("Part one answer: ${dayTwentyOne.partOne()}")
    }

    @Test
    fun testPartTwo() {
//        assertThat(dayTwentyOne.partTwo(), `is`())
        println("Part two answer: ${dayTwentyOne.partTwo()}")
    }
}
