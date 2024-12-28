package id.aflah.universitieslist.data.dao

import androidx.room.Dao
import androidx.room.Query
import id.aflah.universitieslist.data.dbentity.UniversityDbEntity

@Dao
abstract class UniversityDao: BaseDao<UniversityDbEntity>() {

    @Query("SELECT * FROM university_table ORDER BY name")
    abstract suspend fun loadUniversities(): List<UniversityDbEntity>

    @Query("SELECT * FROM university_table WHERE name  LIKE '%' ||  :name || '%' ORDER BY name")
    abstract suspend fun searchUniversitiesByName(name: String): List<UniversityDbEntity>
}