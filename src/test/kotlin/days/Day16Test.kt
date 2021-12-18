package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day16Test {

    private val daySixteen = Day16()

    @Test
    fun testPartOne() {
        assertThat(daySixteen.partOne(), `is`(969L))
        println("Part one answer: ${daySixteen.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(daySixteen.partTwo(), `is`(124921618408L))
        println("Part two answer: ${daySixteen.partTwo()}")
    }
}
