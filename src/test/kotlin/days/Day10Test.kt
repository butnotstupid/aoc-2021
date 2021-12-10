package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day10Test {

    private val dayTen = Day10()

    @Test
    fun testPartOne() {
        assertThat(dayTen.partOne(), `is`(387363))
        println("Part one answer: ${dayTen.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTen.partTwo(), `is`(4330777059L))
        println("Part two answer: ${dayTen.partTwo()}")
    }
}
