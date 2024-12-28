package id.aflah.universitieslist.data.dbentity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "university_table")
data class UniversityDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int? = null,
    @ColumnInfo("country")
    val country: String? = null,
    @ColumnInfo("state_province")
    val stateProvince: String? = null,
    @ColumnInfo("alpha_two_code")
    val alphaTwoCode: String? = null,
    @ColumnInfo("domains")
    val domains: List<String>? = null,
    @ColumnInfo("name")
    val name: String? = null,
    @ColumnInfo("web_pages")
    val webPages: List<String>? = null,
    @ColumnInfo(name = "created_at")
    override val createdAt: String = System.currentTimeMillis().toString(),
    @ColumnInfo(name = "updated_at")
    override var updatedAt: String
): BaseDbEntity()
