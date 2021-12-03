package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day3Test {

    private val dayThree = Day3()

    @Test
    fun testPartOne() {
        assertThat(dayThree.partOne(), `is`(3009600))
        println("Part one answer: ${dayThree.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayThree.partTwo(), `is`(6940518))
        println("Part two answer: ${dayThree.partTwo()}")
    }
}
