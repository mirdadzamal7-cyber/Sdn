package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.StudentEntity
import com.example.ui.SkuViewModel
import com.example.ui.theme.ScoutBrownPrimary
import com.example.ui.theme.ScoutGoldTertiary
import com.example.ui.theme.ScoutGreenDone
import com.example.ui.theme.ScoutRedSecondary

@Composable
fun DataSiswaScreen(
    viewModel: SkuViewModel,
    onStudentSelectedForTesting: () -> Unit,
    modifier: Modifier = Modifier
) {
    val allStudents by viewModel.allStudents.collectAsState()
    val filteredStudents by viewModel.filteredStudents.collectAsState()
    val activeStudent by viewModel.activeStudent.collectAsState()
    val searchQuery by viewModel.studentSearchQuery.collectAsState()
    val selectedClassFilter by viewModel.selectedStudentClassFilter.collectAsState()
    val showDialog by viewModel.showAddStudentDialog.collectAsState()
    val studentToEdit by viewModel.studentToEdit.collectAsState()

    var studentToDelete by remember { mutableStateOf<StudentEntity?>(null) }

    val classTabs = listOf("Semua", "Kelas 1", "Kelas 2", "Kelas 3", "Kelas 4", "Kelas 5", "Kelas 6")

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Hero Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("student_database_header_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = ScoutBrownPrimary.copy(alpha = 0.12f)
                            ) {
                                Text(
                                    text = "DATABASE PRAMUKA",
                                    color = ScoutBrownPrimary,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Daftar Siswa & Kelas",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.ExtraBold,
                                color = ScoutBrownPrimary
                            )
                            Text(
                                text = "SD NEGRI MARGAWANGI (Gudep 06005 & 06006)",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Button(
                            onClick = { viewModel.openAddStudentDialog() },
                            colors = ButtonDefaults.buttonColors(containerColor = ScoutBrownPrimary),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.testTag("add_student_button")
                        ) {
                            Icon(imageVector = Icons.Default.PersonAdd, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = "Tambah", fontSize = 13.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Stats row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(text = "Total Siswa", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Text(
                                    text = "${allStudents.size} Siswa",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ScoutBrownPrimary
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            color = ScoutGreenDone.copy(alpha = 0.1f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(text = "Siswa Dinilai", fontSize = 11.sp, color = ScoutGreenDone)
                                Text(
                                    text = activeStudent?.nama?.split(" ")?.firstOrNull() ?: "-",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = ScoutGreenDone
                                )
                            }
                        }

                        Surface(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            color = ScoutGoldTertiary.copy(alpha = 0.15f)
                        ) {
                            Column(modifier = Modifier.padding(10.dp)) {
                                Text(text = "Tingkat Aktif", fontSize = 11.sp, color = Color(0xFF6D4C41))
                                Text(
                                    text = activeStudent?.kelas ?: "-",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF6D4C41)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Search Bar
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.setStudentSearchQuery(it) },
                placeholder = { Text("Cari nama siswa, kelas, NISN, regu...") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = ScoutBrownPrimary)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("student_search_input"),
                shape = RoundedCornerShape(14.dp),
                singleLine = true
            )
        }

        // Class Filter Horizontal Chips
        item {
            Column {
                Text(
                    text = "Filter Berdasarkan Kelas:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(classTabs) { cls ->
                        val isSelected = selectedClassFilter == cls
                        val count = if (cls == "Semua") {
                            allStudents.size
                        } else {
                            allStudents.count { it.kelas.contains(cls, ignoreCase = true) }
                        }
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.setStudentClassFilter(cls) },
                            label = {
                                Text(
                                    text = if (cls == "Semua") "Semua ($count)" else "$cls ($count)",
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = ScoutBrownPrimary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            }
        }

        // Section Title
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Data Siswa (${filteredStudents.size} ditemukan)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // Student Cards
        if (filteredStudents.isEmpty()) {
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Belum Ada Data Siswa",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = if (searchQuery.isNotBlank()) "Tidak ada siswa yang cocok dengan kata kunci '$searchQuery'." else "Belum ada siswa untuk filter kelas yang dipilih.",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = { viewModel.openAddStudentDialog() },
                            colors = ButtonDefaults.buttonColors(containerColor = ScoutBrownPrimary)
                        ) {
                            Text("+ Tambah Siswa Sekarang")
                        }
                    }
                }
            }
        } else {
            items(
                items = filteredStudents,
                key = { it.id }
            ) { student ->
                val isActive = student.id == activeStudent?.id || student.isActive

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("student_card_${student.id}"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isActive) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
                        else MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = if (isActive) 3.dp else 1.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(CircleShape)
                                        .background(if (isActive) ScoutGreenDone else ScoutBrownPrimary),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = if (isActive) Icons.Default.Star else Icons.Default.Person,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = student.nama,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        if (isActive) {
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Surface(
                                                shape = RoundedCornerShape(4.dp),
                                                color = ScoutGreenDone
                                            ) {
                                                Text(
                                                    text = "AKTIF DINILAI",
                                                    color = Color.White,
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.ExtraBold,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(3.dp))

                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Surface(
                                            shape = RoundedCornerShape(4.dp),
                                            color = ScoutBrownPrimary.copy(alpha = 0.12f)
                                        ) {
                                            Text(
                                                text = student.kelas,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = ScoutBrownPrimary,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }

                                        Surface(
                                            shape = RoundedCornerShape(4.dp),
                                            color = ScoutRedSecondary.copy(alpha = 0.12f)
                                        ) {
                                            Text(
                                                text = student.tingkatSku,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = ScoutRedSecondary,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    }
                                }
                            }

                            // Edit & Delete actions
                            Row {
                                IconButton(
                                    onClick = { viewModel.openEditStudentDialog(student) },
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Ubah Data Siswa",
                                        tint = ScoutBrownPrimary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }

                                IconButton(
                                    onClick = { studentToDelete = student },
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Hapus Siswa",
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Additional info: Regu/Barung, NISN, Catatan
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(10.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Regu/Barung: ${student.reguBarung.ifBlank { '-' }}",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "Gender: ${student.jenisKelamin}",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            if (student.nisn.isNotBlank()) {
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "NISN: ${student.nisn}",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            if (student.catatan.isNotBlank()) {
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Catatan: ${student.catatan}",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Bottom Action: Pilih Siswa Ini untuk Dinilai
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (!isActive) {
                                Button(
                                    onClick = {
                                        viewModel.selectActiveStudent(student)
                                        onStudentSelectedForTesting()
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = ScoutBrownPrimary),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier.testTag("select_student_${student.id}")
                                ) {
                                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = "Pilih & Uji SKU Siswa Ini", fontSize = 12.sp)
                                }
                            } else {
                                OutlinedButton(
                                    onClick = onStudentSelectedForTesting,
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = ScoutGreenDone, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = "Lihat Daftar Butir SKU Siswa Ini", fontSize = 12.sp, color = ScoutGreenDone)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Add / Edit Dialog
    if (showDialog) {
        StudentFormDialog(
            student = studentToEdit,
            onDismiss = { viewModel.closeAddStudentDialog() },
            onSave = { id, nama, kelas, nisn, reguBarung, jenisKelamin, catatan, makeActive ->
                viewModel.saveStudent(id, nama, kelas, nisn, reguBarung, jenisKelamin, catatan, makeActive)
            }
        )
    }

    // Delete Confirmation Dialog
    studentToDelete?.let { stu ->
        AlertDialog(
            onDismissRequest = { studentToDelete = null },
            title = { Text("Hapus Data Siswa") },
            text = { Text("Apakah Anda yakin ingin menghapus data '${stu.nama}' (${stu.kelas}) dari database?") },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteStudent(stu)
                        studentToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Hapus")
                }
            },
            dismissButton = {
                TextButton(onClick = { studentToDelete = null }) {
                    Text("Batal")
                }
            }
        )
    }
}
