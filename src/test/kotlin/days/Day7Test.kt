package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day7Test {

    private val daySeven = Day7()

    @Test
    fun testPartOne() {
        assertThat(daySeven.partOne(), `is`(347509))
        println("Part one answer: ${daySeven.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(daySeven.partTwo(), `is`(98257206))
        println("Part two answer: ${daySeven.partTwo()}")
    }
}
