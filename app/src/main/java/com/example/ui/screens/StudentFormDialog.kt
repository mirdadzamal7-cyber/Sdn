package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.StudentEntity
import com.example.ui.theme.ScoutBrownPrimary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StudentFormDialog(
    student: StudentEntity?,
    onDismiss: () -> Unit,
    onSave: (
        id: Int,
        nama: String,
        kelas: String,
        nisn: String,
        reguBarung: String,
        jenisKelamin: String,
        catatan: String,
        makeActive: Boolean
    ) -> Unit,
    modifier: Modifier = Modifier
) {
    val isEditing = student != null
    var nama by remember { mutableStateOf(student?.nama ?: "") }
    var kelas by remember { mutableStateOf(student?.kelas ?: "Kelas 4") }
    var nisn by remember { mutableStateOf(student?.nisn ?: "") }
    var reguBarung by remember { mutableStateOf(student?.reguBarung ?: "") }
    var jenisKelamin by remember { mutableStateOf(student?.jenisKelamin ?: "Laki-laki") }
    var catatan by remember { mutableStateOf(student?.catatan ?: "") }
    var makeActive by remember { mutableStateOf(student?.isActive ?: true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val classOptions = listOf("Kelas 1", "Kelas 2", "Kelas 3", "Kelas 4", "Kelas 5", "Kelas 6")
    val genderOptions = listOf("Laki-laki", "Perempuan")

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .testTag("student_form_dialog"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (isEditing) Icons.Default.Person else Icons.Default.School,
                            contentDescription = null,
                            tint = ScoutBrownPrimary,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Column {
                            Text(
                                text = if (isEditing) "Ubah Data Siswa" else "Tambah Data Siswa",
                                fontWeight = FontWeight.Bold,
                                fontSize = 17.sp,
                                color = ScoutBrownPrimary
                            )
                            Text(
                                text = "Database Siswa SD Negri Margawangi",
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Error alert
                errorMessage?.let { msg ->
                    Text(
                        text = msg,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // Nama Siswa
                OutlinedTextField(
                    value = nama,
                    onValueChange = {
                        nama = it
                        if (it.isNotBlank()) errorMessage = null
                    },
                    label = { Text("Nama Lengkap Siswa *") },
                    placeholder = { Text("Contoh: Ahmad Fauzi") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("student_name_input"),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Pilihan Kelas (Kelas 1 - Kelas 6)
                Text(
                    text = "Tingkat Kelas SD:",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    classOptions.forEach { opt ->
                        val isSelected = kelas == opt
                        FilterChip(
                            selected = isSelected,
                            onClick = { kelas = opt },
                            label = { Text(opt, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = ScoutBrownPrimary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Jenis Kelamin
                Text(
                    text = "Jenis Kelamin:",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    genderOptions.forEach { gen ->
                        val isSelected = jenisKelamin == gen
                        FilterChip(
                            selected = isSelected,
                            onClick = { jenisKelamin = gen },
                            label = { Text(gen, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = ScoutBrownPrimary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Regu / Barung
                OutlinedTextField(
                    value = reguBarung,
                    onValueChange = { reguBarung = it },
                    label = { Text("Regu (Penggalang) / Barung (Siaga)") },
                    placeholder = { Text("Misal: Regu Rajawali / Barung Merah") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // NISN / No Induk
                OutlinedTextField(
                    value = nisn,
                    onValueChange = { nisn = it },
                    label = { Text("NISN / Nomor Induk (Opsional)") },
                    placeholder = { Text("Contoh: 0112345678") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Catatan Khusus
                OutlinedTextField(
                    value = catatan,
                    onValueChange = { catatan = it },
                    label = { Text("Catatan Tambahan (Opsional)") },
                    placeholder = { Text("Catatan tugas, kepemimpinan, dll.") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 2,
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Checkbox: Jadikan Siswa Aktif
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(
                        checked = makeActive,
                        onCheckedChange = { makeActive = it }
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Jadikan siswa aktif untuk penilaian SKU sekarang",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Batal")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        onClick = {
                            if (nama.isBlank()) {
                                errorMessage = "Nama siswa wajib diisi!"
                                return@Button
                            }
                            onSave(
                                student?.id ?: 0,
                                nama,
                                kelas,
                                nisn,
                                reguBarung,
                                jenisKelamin,
                                catatan,
                                makeActive
                            )
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ScoutBrownPrimary),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("save_student_button")
                    ) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = null, modifier = Modifier.padding(end = 6.dp))
                        Text(if (isEditing) "Simpan Perubahan" else "Tambah Siswa")
                    }
                }
            }
        }
    }
}
