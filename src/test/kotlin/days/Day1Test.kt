package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day1Test {

    private val dayOne = Day1()

    @Test
    fun testPartOne() {
        assertThat(dayOne.partOne(), `is`(1393))
        println("Part one answer: ${dayOne.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayOne.partTwo(), `is`(1359))
        println("Part two answer: ${dayOne.partTwo()}")
    }
}
