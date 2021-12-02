package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day2Test {

    private val dayTwo = Day2()

    @Test
    fun testPartOne() {
        assertThat(dayTwo.partOne(), `is`(2039256L))
        println("Part one answer: ${dayTwo.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwo.partTwo(), `is`(1856459736L))
        println("Part two answer: ${dayTwo.partTwo()}")
    }
}
