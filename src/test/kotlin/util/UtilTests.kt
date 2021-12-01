package util

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsIterableContainingInOrder.contains
import org.hamcrest.core.Is.`is`
import org.junit.Ignore
import org.junit.Test

class UtilTests {

    @Ignore
    @Test
    fun testReadInputAsString() {
        val testInputAsString = InputReader.getInputAsString(1)
        assertThat(testInputAsString, `is`("this\nis\na\ntest input\nfile\n"))
    }

    @Ignore
    @Test
    fun testReadInputAsList() {
        val testInputAsList = InputReader.getInputAsList(1)
        assertThat(testInputAsList, contains("this", "is", "a", "test input", "file"))
    }
}
