package id.aflah.universitieslist.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update

abstract class BaseDao<E> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(entity: E): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertAll(entities: List<E>): List<Long>

    @Update
    abstract suspend fun update(entity: E)

    @Update
    abstract suspend fun update(entityList: List<E>)

    @Delete
    abstract suspend fun deleteEntity(entity: E)

    @Transaction
    open suspend fun upsert(entity: E) {
        val insertResult = insert(entity)
        if (insertResult == -1L) {
            update(entity)
        }
    }

    @Transaction
    open suspend fun upsert(entities: List<E>) {
        val insertResults = insertAll(entities)
        val shouldBeUpdatedEntities = arrayListOf<E>()

        for (i in insertResults.indices) {
            if (insertResults[i] == -1L) {
                shouldBeUpdatedEntities.add(entities[i])
            }
        }

        if (shouldBeUpdatedEntities.isNotEmpty()) {
            update(shouldBeUpdatedEntities)
        }
    }
}