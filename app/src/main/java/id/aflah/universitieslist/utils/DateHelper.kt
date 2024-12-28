package id.aflah.universitieslist.utils

import java.util.Calendar

object DateHelper {
    fun getTodayMillis() = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis
}