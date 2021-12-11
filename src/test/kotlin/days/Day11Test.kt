package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day11Test {

    private val dayEleven = Day11()

    @Test
    fun testPartOne() {
        assertThat(dayEleven.partOne(), `is`(1735))
        println("Part one answer: ${dayEleven.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayEleven.partTwo(), `is`(400))
        println("Part two answer: ${dayEleven.partTwo()}")
    }
}
