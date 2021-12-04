package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day4Test {

    private val dayFour = Day4()

    @Test
    fun testPartOne() {
        assertThat(dayFour.partOne(), `is`(23177L))
        println("Part one answer: ${dayFour.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFour.partTwo(), `is`(6804L))
        println("Part two answer: ${dayFour.partTwo()}")
    }
}
