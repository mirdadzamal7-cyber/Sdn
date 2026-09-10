package com.example.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.data.model.StudentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM students ORDER BY kelas ASC, nama ASC")
    fun getAllStudents(): Flow<List<StudentEntity>>

    @Query("SELECT * FROM students WHERE kelas = :kelas ORDER BY nama ASC")
    fun getStudentsByClass(kelas: String): Flow<List<StudentEntity>>

    @Query("SELECT * FROM students WHERE id = :id LIMIT 1")
    fun getStudentById(id: Int): Flow<StudentEntity?>

    @Query("SELECT * FROM students WHERE isActive = 1 LIMIT 1")
    fun getActiveStudent(): Flow<StudentEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(students: List<StudentEntity>)

    @Update
    suspend fun updateStudent(student: StudentEntity)

    @Delete
    suspend fun deleteStudent(student: StudentEntity)

    @Query("DELETE FROM students WHERE id = :id")
    suspend fun deleteStudentById(id: Int)

    @Query("UPDATE students SET isActive = 0")
    suspend fun clearActiveStatus()

    @Query("UPDATE students SET isActive = 1 WHERE id = :id")
    suspend fun markActive(id: Int)

    @Transaction
    suspend fun selectActiveStudent(id: Int) {
        clearActiveStatus()
        markActive(id)
    }

    @Query("SELECT COUNT(*) FROM students")
    fun getStudentCount(): Flow<Int>
}
