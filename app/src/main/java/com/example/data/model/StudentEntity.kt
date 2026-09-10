package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val kelas: String, // "Kelas 1", "Kelas 2", "Kelas 3", "Kelas 4", "Kelas 5", "Kelas 6"
    val nisn: String = "",
    val reguBarung: String = "", // misal "Regu Rajawali" / "Barung Merah"
    val jenisKelamin: String = "Laki-laki", // "Laki-laki" atau "Perempuan"
    val tingkatSku: String = "Penggalang Ramu", // Tingkat SKU: Siaga Mula/Bantu/Tata, Penggalang Ramu/Rakit/Terap
    val isActive: Boolean = false,
    val catatan: String = ""
)
