package id.aflah.universitieslist.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

class LongExtensionTest {
    @Test
    fun `test given non-null long number when orZero extension called then should return its number`() {
        // then
        assertEquals(1L, 1L.orZero())
        assertEquals(0L, 0L.orZero())
    }

    @Test
    fun `test given null long number when orZero extension called then should return zero`() {
        // then
        assertEquals(0L, null.orZero())
    }
}