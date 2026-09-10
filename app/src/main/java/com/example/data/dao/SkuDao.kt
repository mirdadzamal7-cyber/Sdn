package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.SkuItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SkuDao {
    @Query("SELECT * FROM sku_items ORDER BY pointNumber ASC")
    fun getAllItems(): Flow<List<SkuItemEntity>>

    @Query("SELECT * FROM sku_items WHERE level = :level ORDER BY pointNumber ASC")
    fun getItemsByLevel(level: String): Flow<List<SkuItemEntity>>

    @Query("SELECT * FROM sku_items WHERE id = :id")
    fun getItemById(id: Int): Flow<SkuItemEntity?>

    @Query("SELECT COUNT(*) FROM sku_items WHERE level = :level")
    fun getTotalCountByLevel(level: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM sku_items WHERE level = :level AND isCompleted = 1")
    fun getCompletedCountByLevel(level: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM sku_items")
    fun getTotalItemCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM sku_items WHERE isCompleted = 1")
    fun getTotalCompletedCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(items: List<SkuItemEntity>)

    @Update
    suspend fun updateItem(item: SkuItemEntity)

    @Query("""
        UPDATE sku_items 
        SET isCompleted = :isCompleted, 
            completionDate = :completionDate, 
            examinerName = :examinerName, 
            notes = :notes 
        WHERE id = :id
    """)
    suspend fun updateCompletionStatus(
        id: Int,
        isCompleted: Boolean,
        completionDate: String,
        examinerName: String,
        notes: String
    )
}
