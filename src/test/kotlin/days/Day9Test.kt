package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day9Test {

    private val dayNine = Day9()

    @Test
    fun testPartOne() {
        assertThat(dayNine.partOne(), `is`(417))
        println("Part one answer: ${dayNine.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayNine.partTwo(), `is`(1148965))
        println("Part two answer: ${dayNine.partTwo()}")
    }
}
