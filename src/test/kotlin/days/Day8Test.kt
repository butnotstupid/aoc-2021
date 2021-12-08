package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day8Test {

    private val dayEight = Day8()

    @Test
    fun testPartOne() {
        assertThat(dayEight.partOne(), `is`(530))
        println("Part one answer: ${dayEight.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayEight.partTwo(), `is`(1051087L))
        println("Part two answer: ${dayEight.partTwo()}")
    }
}
