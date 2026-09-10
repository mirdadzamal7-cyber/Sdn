package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.example.data.model.SkuItemEntity
import com.example.ui.theme.ScoutBrownPrimary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SkuTestingDialog(
    item: SkuItemEntity,
    defaultExaminer: String,
    onDismiss: () -> Unit,
    onSave: (id: Int, isCompleted: Boolean, date: String, examiner: String, notes: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val currentDateStr = remember {
        SimpleDateFormat("dd MMM yyyy", Locale.forLanguageTag("id-ID")).format(Date())
    }

    var isCompleted by remember { mutableStateOf(item.isCompleted) }
    var completionDate by remember {
        mutableStateOf(item.completionDate.ifBlank { currentDateStr })
    }
    var examinerName by remember {
        mutableStateOf(item.examinerName.ifBlank { defaultExaminer })
    }
    var notes by remember { mutableStateOf(item.notes) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .testTag("sku_testing_dialog"),
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
                // Top Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Catat Pengujian Butir ${item.pointNumber}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = ScoutBrownPrimary
                    )
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Batal")
                    }
                }

                Text(
                    text = item.title,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                // Switch Lulus
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Status Kelulusan:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Text(
                            text = if (isCompleted) "Dinyatakan Lulus" else "Belum Memenuhi Syarat",
                            color = if (isCompleted) Color(0xFF2E7D32) else Color.Gray,
                            fontSize = 12.sp
                        )
                    }

                    Switch(
                        checked = isCompleted,
                        onCheckedChange = { isCompleted = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color(0xFF2E7D32)
                        ),
                        modifier = Modifier.testTag("testing_status_switch")
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Tanggal Pengujian
                OutlinedTextField(
                    value = completionDate,
                    onValueChange = { completionDate = it },
                    label = { Text("Tanggal Pengujian") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Today,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("testing_date_input"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Nama Pembina Penguji
                OutlinedTextField(
                    value = examinerName,
                    onValueChange = { examinerName = it },
                    label = { Text("Nama Pembina Penguji") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("testing_examiner_input"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Catatan / Keterangan Pembina
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    label = { Text("Catatan / Evaluasi Pengujian") },
                    placeholder = { Text("Contoh: Menguasai simpul pangkal dan jangkar dengan sangat baik.") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("testing_notes_input"),
                    shape = RoundedCornerShape(10.dp),
                    minLines = 2,
                    maxLines = 4
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Action buttons
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
                            onSave(
                                item.id,
                                isCompleted,
                                if (isCompleted) completionDate else "",
                                if (isCompleted) examinerName else "",
                                notes
                            )
                        },
                        modifier = Modifier
                            .weight(1.5f)
                            .testTag("save_testing_button"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ScoutBrownPrimary)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Simpan Hasil", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
