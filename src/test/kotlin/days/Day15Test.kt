package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day15Test {

    private val dayFifteen = Day15()

    @Test
    fun testPartOne() {
        assertThat(dayFifteen.partOne(), `is`(498))
        println("Part one answer: ${dayFifteen.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFifteen.partTwo(), `is`(2901))
        println("Part two answer: ${dayFifteen.partTwo()}")
    }
}
