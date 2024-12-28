package id.aflah.universitieslist.data.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UniversityTypeConverter {
    private val gson : Gson by lazy {
        Gson()
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String = gson.toJson(value)

    @TypeConverter
    fun toStringList(value: String): List<String> =
        gson.fromJson(value, object : TypeToken<List<String>>() {}.type)
}