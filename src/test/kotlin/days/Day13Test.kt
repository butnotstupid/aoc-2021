package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day13Test {

    private val dayThirteen = Day13()

    @Test
    fun testPartOne() {
        assertThat(dayThirteen.partOne(), `is`(770))
        println("Part one answer: ${dayThirteen.partOne()}")
    }

    @Test
    fun testPartTwo() {
        val partTwoAnswer = dayThirteen.partTwo()
        assertThat(partTwoAnswer, `is`("EPUELPBR"))
        println("Part two answer: $partTwoAnswer")
    }
}
