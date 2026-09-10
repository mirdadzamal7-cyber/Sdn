package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.SkuItemEntity
import com.example.data.model.SkuLevel
import com.example.ui.theme.ScoutBrownPrimary
import com.example.ui.theme.ScoutGoldTertiary
import com.example.ui.theme.ScoutRedSecondary

fun getReadableLevelLabel(levelName: String): String {
    return when (levelName) {
        "SIAGA_MULA" -> "Kelas 1 (Siaga Mula)"
        "SIAGA_BANTU" -> "Kelas 2 (Siaga Bantu)"
        "SIAGA_TATA" -> "Kelas 3 (Siaga Tata)"
        "RAMU" -> "Kelas 4 (Penggalang Ramu)"
        "RAKIT" -> "Kelas 5 (Penggalang Rakit)"
        "TERAP" -> "Kelas 6 (Penggalang Terap)"
        else -> levelName
    }
}

@Composable
fun SkuItemCard(
    item: SkuItemEntity,
    showLevelBadge: Boolean = false,
    searchQuery: String = "",
    onDetailClick: () -> Unit,
    onTestingClick: () -> Unit,
    onQuickToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val areaColor = getAreaColor(item.area)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .testTag("sku_card_${item.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isCompleted) Color(0xFFF1F8E9) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        border = if (item.isCompleted) {
            CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF81C784)))
        } else null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // Top Row: Number & Area Badge + Checkbox
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    // Number Circle
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(if (item.isCompleted) Color(0xFF2E7D32) else ScoutBrownPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${item.pointNumber}",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    // Area Badge
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = areaColor.copy(alpha = 0.12f)
                    ) {
                        Text(
                            text = item.area,
                            color = areaColor,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }

                    // Level Badge if in global search
                    if (showLevelBadge) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = ScoutBrownPrimary.copy(alpha = 0.1f)
                        ) {
                            Text(
                                text = getReadableLevelLabel(item.level),
                                color = ScoutBrownPrimary,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }
                }

                // Quick complete checkbox
                Checkbox(
                    checked = item.isCompleted,
                    onCheckedChange = { onQuickToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF2E7D32),
                        checkmarkColor = Color.White
                    ),
                    modifier = Modifier.testTag("sku_checkbox_${item.id}")
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Panduan Preview
            Text(
                text = "Panduan: ${item.panduanPembina}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            // Completed metadata stamp if tested
            AnimatedVisibility(visible = item.isCompleted) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFE8F5E9))
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Lulus",
                            tint = Color(0xFF2E7D32),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Lulus pada: ${item.completionDate.ifBlank { "Telah diverifikasi" }}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF1B5E20)
                        )
                    }
                    if (item.examinerName.isNotBlank()) {
                        Text(
                            text = "Penguji: ${item.examinerName}",
                            fontSize = 11.sp,
                            color = Color(0xFF2E7D32)
                        )
                    }
                    if (item.notes.isNotBlank()) {
                        Text(
                            text = "Catatan: ${item.notes}",
                            fontSize = 10.sp,
                            color = Color(0xFF33691E)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Bottom Actions: Buka Panduan & Catat Pengujian
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onDetailClick,
                    modifier = Modifier.testTag("detail_button_${item.id}")
                ) {
                    Icon(
                        imageVector = Icons.Default.MenuBook,
                        contentDescription = null,
                        tint = ScoutBrownPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Panduan & Materi",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ScoutBrownPrimary
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                OutlinedButton(
                    onClick = onTestingClick,
                    modifier = Modifier.testTag("testing_button_${item.id}"),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (item.isCompleted) Color(0xFF2E7D32) else ScoutRedSecondary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.EditNote,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (item.isCompleted) "Ubah Catatan" else "Uji Pembina",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
