package com.example.data.repository

import com.example.data.dao.ProfileDao
import com.example.data.dao.SkuDao
import com.example.data.model.ScoutProfileEntity
import com.example.data.model.SkuItemEntity
import com.example.data.source.InitialSkuData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class SkuRepository(
    private val skuDao: SkuDao,
    private val profileDao: ProfileDao
) {

    fun getItemsByLevel(level: String): Flow<List<SkuItemEntity>> {
        return skuDao.getItemsByLevel(level)
    }

    fun getAllItems(): Flow<List<SkuItemEntity>> {
        return skuDao.getAllItems()
    }

    fun getItemById(id: Int): Flow<SkuItemEntity?> {
        return skuDao.getItemById(id)
    }

    fun getProfile(): Flow<ScoutProfileEntity?> {
        return profileDao.getProfile()
    }

    fun getCompletedCountByLevel(level: String): Flow<Int> {
        return skuDao.getCompletedCountByLevel(level)
    }

    fun getTotalCountByLevel(level: String): Flow<Int> {
        return skuDao.getTotalCountByLevel(level)
    }

    fun getTotalItemCount(): Flow<Int> {
        return skuDao.getTotalItemCount()
    }

    fun getTotalCompletedCount(): Flow<Int> {
        return skuDao.getTotalCompletedCount()
    }

    suspend fun updateCompletionStatus(
        id: Int,
        isCompleted: Boolean,
        date: String,
        examiner: String,
        notes: String
    ) = withContext(Dispatchers.IO) {
        skuDao.updateCompletionStatus(id, isCompleted, date, examiner, notes)
    }

    suspend fun updateProfile(profile: ScoutProfileEntity) = withContext(Dispatchers.IO) {
        profileDao.updateProfile(profile)
    }

    suspend fun ensureDataSeeded() = withContext(Dispatchers.IO) {
        val count = skuDao.getAllItems().firstOrNull()?.size ?: 0
        if (count < 60) {
            skuDao.insertAll(InitialSkuData.getInitialSkuItems())
        }
        val existingProfile = profileDao.getProfile().firstOrNull()
        if (existingProfile == null || existingProfile.pangkalan != "SD NEGRI MARGAWANGI") {
            profileDao.insertProfile(InitialSkuData.defaultProfile)
        }
    }
}
