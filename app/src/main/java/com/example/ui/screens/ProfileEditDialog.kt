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
import androidx.compose.material.icons.filled.Save
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.ScoutProfileEntity
import com.example.ui.theme.ScoutBrownPrimary

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileEditDialog(
    profile: ScoutProfileEntity,
    onDismiss: () -> Unit,
    onSave: (name: String, gradeClass: String, regu: String, pangkalan: String, gudep: String, pembina: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf(profile.fullName) }
    var gradeClass by remember { mutableStateOf(profile.gradeClass) }
    var regu by remember { mutableStateOf(profile.reguName) }
    var pangkalan by remember { mutableStateOf(profile.pangkalan.ifBlank { "SD NEGRI MARGAWANGI" }) }
    var gudep by remember { mutableStateOf(profile.gudep.ifBlank { "GUDEP 06005 DAN 06006" }) }
    var pembina by remember { mutableStateOf(profile.pembinaName.ifBlank { "Kak Pembina SD Negri Margawangi" }) }

    val gradeOptions = listOf("Kelas 1 SD", "Kelas 2 SD", "Kelas 3 SD", "Kelas 4 SD", "Kelas 5 SD", "Kelas 6 SD")

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .testTag("profile_edit_dialog"),
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Profil Anggota Pramuka",
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = ScoutBrownPrimary
                        )
                        Text(
                            text = "SD NEGRI MARGAWANGI",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Tutup")
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nama Siswa / Anggota") },
                    modifier = Modifier.fillMaxWidth().testTag("input_profile_name"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Pilihan Kelas 1 s/d 6 SD
                Text(
                    text = "Tingkat Kelas SD:",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    gradeOptions.forEach { opt ->
                        val isSelected = gradeClass == opt
                        FilterChip(
                            selected = isSelected,
                            onClick = { gradeClass = opt },
                            label = { Text(opt, fontSize = 11.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = ScoutBrownPrimary,
                                selectedLabelColor = Color.White
                            ),
                            modifier = Modifier.testTag("grade_chip_$opt")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = regu,
                    onValueChange = { regu = it },
                    label = { Text("Nama Regu / Barung") },
                    placeholder = { Text("Contoh: Regu Rajawali / Barung Merah") },
                    modifier = Modifier.fillMaxWidth().testTag("input_profile_regu"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = pangkalan,
                    onValueChange = { pangkalan = it },
                    label = { Text("Pangkalan Sekolah") },
                    modifier = Modifier.fillMaxWidth().testTag("input_profile_pangkalan"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = gudep,
                    onValueChange = { gudep = it },
                    label = { Text("Nomor Gugus Depan") },
                    modifier = Modifier.fillMaxWidth().testTag("input_profile_gudep"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = pembina,
                    onValueChange = { pembina = it },
                    label = { Text("Nama Pembina Pasukan / Perindukan") },
                    modifier = Modifier.fillMaxWidth().testTag("input_profile_pembina"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Batal")
                    }

                    Button(
                        onClick = {
                            onSave(name, gradeClass, regu, pangkalan, gudep, pembina)
                            onDismiss()
                        },
                        modifier = Modifier
                            .weight(1.5f)
                            .testTag("save_profile_button"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ScoutBrownPrimary)
                    ) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Simpan Data", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
