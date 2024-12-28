package id.aflah.universitieslist.utils

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.util.Calendar

class DateHelperTest {
    @Test
    fun `test getTodayMillis returns the start of the day in milliseconds`() {
        // inject
        val todayMillis = DateHelper.getTodayMillis()
        val expectedMillis = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        // then
        assertEquals(expectedMillis, todayMillis)
    }

    @Test
    fun `test getTodayMillis is smaller than a current time millis if executed not in the beginning of day 00-00`() {
        // inject
        val todayMillis = DateHelper.getTodayMillis()
        val expectedMillis = Calendar.getInstance().timeInMillis

        // then
        assertTrue(expectedMillis > todayMillis)
    }
}