package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day17Test {

    private val daySeventeen = Day17()

    @Test
    fun testPartOne() {
        assertThat(daySeventeen.partOne(), `is`(3570))
        println("Part one answer: ${daySeventeen.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(daySeventeen.partTwo(), `is`(1919))
        println("Part two answer: ${daySeventeen.partTwo()}")
    }
}
