package com.example.data.repository

import com.example.data.dao.ProfileDao
import com.example.data.dao.SkuDao
import com.example.data.dao.StudentDao
import com.example.data.model.ScoutProfileEntity
import com.example.data.model.SkuItemEntity
import com.example.data.model.StudentEntity
import com.example.data.source.InitialSkuData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class SkuRepository(
    private val skuDao: SkuDao,
    private val profileDao: ProfileDao,
    private val studentDao: StudentDao
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

    // --- Student (Data Siswa & Kelas) Operations ---

    fun getAllStudents(): Flow<List<StudentEntity>> {
        return studentDao.getAllStudents()
    }

    fun getStudentsByClass(kelas: String): Flow<List<StudentEntity>> {
        return studentDao.getStudentsByClass(kelas)
    }

    fun getStudentById(id: Int): Flow<StudentEntity?> {
        return studentDao.getStudentById(id)
    }

    fun getActiveStudent(): Flow<StudentEntity?> {
        return studentDao.getActiveStudent()
    }

    fun getStudentCount(): Flow<Int> {
        return studentDao.getStudentCount()
    }

    suspend fun insertStudent(student: StudentEntity): Long = withContext(Dispatchers.IO) {
        val id = studentDao.insertStudent(student)
        if (student.isActive) {
            studentDao.selectActiveStudent(id.toInt())
            val curProfile = profileDao.getProfile().firstOrNull() ?: InitialSkuData.defaultProfile
            profileDao.updateProfile(
                curProfile.copy(
                    fullName = student.nama,
                    gradeClass = if (student.kelas.contains("SD", ignoreCase = true)) student.kelas else "${student.kelas} SD",
                    reguName = student.reguBarung.ifBlank { curProfile.reguName }
                )
            )
        }
        id
    }

    suspend fun updateStudent(student: StudentEntity) = withContext(Dispatchers.IO) {
        studentDao.updateStudent(student)
        if (student.isActive) {
            val curProfile = profileDao.getProfile().firstOrNull() ?: InitialSkuData.defaultProfile
            profileDao.updateProfile(
                curProfile.copy(
                    fullName = student.nama,
                    gradeClass = if (student.kelas.contains("SD", ignoreCase = true)) student.kelas else "${student.kelas} SD",
                    reguName = student.reguBarung.ifBlank { curProfile.reguName }
                )
            )
        }
    }

    suspend fun deleteStudent(student: StudentEntity) = withContext(Dispatchers.IO) {
        studentDao.deleteStudent(student)
    }

    suspend fun selectActiveStudent(student: StudentEntity) = withContext(Dispatchers.IO) {
        studentDao.selectActiveStudent(student.id)
        val curProfile = profileDao.getProfile().firstOrNull() ?: InitialSkuData.defaultProfile
        profileDao.updateProfile(
            curProfile.copy(
                fullName = student.nama,
                gradeClass = if (student.kelas.contains("SD", ignoreCase = true)) student.kelas else "${student.kelas} SD",
                reguName = student.reguBarung.ifBlank { curProfile.reguName }
            )
        )
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
        try {
            val count = skuDao.getAllItems().firstOrNull()?.size ?: 0
            if (count < 60) {
                skuDao.insertAll(InitialSkuData.getInitialSkuItems())
            }
            val existingProfile = profileDao.getProfile().firstOrNull()
            if (existingProfile == null || existingProfile.pangkalan != "SD NEGRI MARGAWANGI") {
                profileDao.insertProfile(InitialSkuData.defaultProfile)
            }
            val studentCount = studentDao.getStudentCount().firstOrNull() ?: 0
            if (studentCount == 0) {
                studentDao.insertAll(InitialSkuData.getInitialStudents())
            }
        } catch (e: Throwable) {
            android.util.Log.e("SkuRepository", "Error seeding initial data", e)
        }
    }
}
