package id.aflah.universitieslist.data.localdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.aflah.universitieslist.data.dao.UniversityDao
import id.aflah.universitieslist.data.dbentity.UniversityDbEntity
import id.aflah.universitieslist.data.typeconverter.UniversityTypeConverter

@TypeConverters(UniversityTypeConverter::class)
@Database(
    entities = [UniversityDbEntity::class],
    version = UniversityDatabase.DB_VERSION,
    exportSchema = true
)
abstract class UniversityDatabase: RoomDatabase() {

    abstract fun universityDao(): UniversityDao

    companion object {
        const val DB_PASS_PHRASE = "university_pass_phrase"
        const val DB_NAME = "university.db"
        const val DB_VERSION = 1
    }
}