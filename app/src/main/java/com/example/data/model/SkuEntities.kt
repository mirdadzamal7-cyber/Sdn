package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class ScoutGolongan(val displayName: String, val description: String) {
    SIAGA("Pramuka Siaga", "Kelas 1 - 3 SD (Usia 7-10 Tahun)"),
    PENGGALANG("Pramuka Penggalang", "Kelas 4 - 6 SD (Usia 11-15 Tahun)")
}

enum class SkuLevel(
    val idCode: String,
    val displayName: String,
    val gradeLevel: String,
    val tkuTitle: String,
    val golongan: ScoutGolongan,
    val manggarCount: Int,
    val badgeTitle: String
) {
    // SD KELAS 1 s/d 3: GOLONGAN SIAGA
    SIAGA_MULA("SIAGA_MULA", "Siaga Mula (Kelas 1)", "Kelas 1", "Siaga Mula", ScoutGolongan.SIAGA, 1, "Tingkat Pertama Siaga"),
    SIAGA_BANTU("SIAGA_BANTU", "Siaga Bantu (Kelas 2)", "Kelas 2", "Siaga Bantu", ScoutGolongan.SIAGA, 2, "Tingkat Kedua Siaga"),
    SIAGA_TATA("SIAGA_TATA", "Siaga Tata (Kelas 3)", "Kelas 3", "Siaga Tata", ScoutGolongan.SIAGA, 3, "Tingkat Ketiga Siaga"),

    // SD KELAS 4 s/d 6: GOLONGAN PENGGALANG
    RAMU("RAMU", "Penggalang Ramu (Kelas 4)", "Kelas 4", "Penggalang Ramu", ScoutGolongan.PENGGALANG, 1, "Tingkat Pertama Penggalang"),
    RAKIT("RAKIT", "Penggalang Rakit (Kelas 5)", "Kelas 5", "Penggalang Rakit", ScoutGolongan.PENGGALANG, 2, "Tingkat Kedua Penggalang"),
    TERAP("TERAP", "Penggalang Terap (Kelas 6)", "Kelas 6", "Penggalang Terap", ScoutGolongan.PENGGALANG, 3, "Tingkat Ketiga Penggalang")
}

enum class SkuArea(val displayName: String, val subtitle: String) {
    SPIRITUAL("Spiritual", "Keagamaan & Toleransi"),
    EMOSIONAL("Emosional", "Kode Kehormatan"),
    SOSIAL("Sosial", "Masyarakat & Keluarga"),
    INTELEKTUAL("Intelektual", "Keterampilan, Sandi & Simpul"),
    FISIK("Fisik", "Jasmani, PBB & Olahraga")
}

@Entity(tableName = "sku_items")
data class SkuItemEntity(
    @PrimaryKey val id: Int,
    val level: String, // SIAGA_MULA, SIAGA_BANTU, SIAGA_TATA, RAMU, RAKIT, TERAP
    val area: String,  // SPIRITUAL, EMOSIONAL, SOSIAL, INTELEKTUAL, FISIK
    val pointNumber: Int,
    val title: String,
    val panduanPembina: String,
    val materi: String,
    val isCompleted: Boolean = false,
    val completionDate: String = "",
    val examinerName: String = "",
    val notes: String = ""
)

@Entity(tableName = "scout_profile")
data class ScoutProfileEntity(
    @PrimaryKey val id: Int = 1,
    val fullName: String = "Pratama Margawangi",
    val gradeClass: String = "Kelas 4 SD",
    val reguName: String = "Regu Rajawali (Putra 06005)",
    val pangkalan: String = "SD NEGRI MARGAWANGI",
    val gudep: String = "GUDEP 06005 DAN 06006",
    val pembinaName: String = "Kak Pembina SD Negri Margawangi"
)
