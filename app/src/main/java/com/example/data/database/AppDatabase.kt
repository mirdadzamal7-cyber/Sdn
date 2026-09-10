package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.dao.ProfileDao
import com.example.data.dao.SkuDao
import com.example.data.dao.StudentDao
import com.example.data.model.ScoutProfileEntity
import com.example.data.model.SkuItemEntity
import com.example.data.model.StudentEntity
import com.example.data.source.InitialSkuData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [SkuItemEntity::class, ScoutProfileEntity::class, StudentEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun skuDao(): SkuDao
    abstract fun profileDao(): ProfileDao
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sku_penggalang_db"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return getDatabase(context)
        }
    }
}
