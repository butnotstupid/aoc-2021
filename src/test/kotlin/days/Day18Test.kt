package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day18Test {

    private val dayEighteen = Day18()

    @Test
    fun testPartOne() {
        assertThat(dayEighteen.partOne(), `is`(4207L))
        println("Part one answer: ${dayEighteen.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayEighteen.partTwo(), `is`(4635L))
        println("Part two answer: ${dayEighteen.partTwo()}")
    }
}
