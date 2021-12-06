package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class Day6Test {

    private val daySix = Day6()

    @Test
    fun testPartOne() {
        assertThat(daySix.partOne(), `is`(386640L))
        println("Part one answer: ${daySix.partOne()}")
    }

    @Test
    fun testPartTwo() {
        assertThat(daySix.partTwo(), `is`(1733403626279L))
        println("Part two answer: ${daySix.partTwo()}")
    }
}
