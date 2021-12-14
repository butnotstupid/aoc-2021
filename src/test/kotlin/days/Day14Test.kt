package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day14Test {

    private val dayFourteen = Day14()

    @Test
    fun testPartOne() {
        assertThat(dayFourteen.partOne(), `is`(3697))
        println("Part one answer: ${dayFourteen.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFourteen.partTwo(), `is`(4371307836157L))
        println("Part two answer: ${dayFourteen.partTwo()}")
    }
}
