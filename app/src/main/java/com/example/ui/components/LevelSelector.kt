package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ScoutGolongan
import com.example.data.model.SkuLevel
import com.example.ui.theme.ScoutBrownPrimary
import com.example.ui.theme.ScoutRedSecondary

@Composable
fun LevelSelector(
    selectedLevel: SkuLevel,
    onLevelSelected: (SkuLevel) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .testTag("level_selector_container")
    ) {
        // Section Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pilih Tingkat Kelas SD (1 s/d 6):",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Surface(
                shape = RoundedCornerShape(6.dp),
                color = if (selectedLevel.golongan == ScoutGolongan.SIAGA) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
            ) {
                Text(
                    text = if (selectedLevel.golongan == ScoutGolongan.SIAGA) "Golongan Siaga" else "Golongan Penggalang",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (selectedLevel.golongan == ScoutGolongan.SIAGA) Color(0xFF2E7D32) else ScoutRedSecondary,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Horizontal Scroll of 6 SD Grades
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SkuLevel.values().forEach { level ->
                val isSelected = selectedLevel == level
                val isSiaga = level.golongan == ScoutGolongan.SIAGA
                val manggarColor = if (isSiaga) Color(0xFF2E7D32) else ScoutRedSecondary

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isSelected) ScoutBrownPrimary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    shadowElevation = if (isSelected) 3.dp else 0.dp,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onLevelSelected(level) }
                        .testTag("level_tab_${level.name.lowercase()}")
                ) {
                    Column(
                        modifier = Modifier
                            .width(115.dp)
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Manggar visual: Green for Siaga, Red for Penggalang
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(level.manggarCount) {
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 1.dp)
                                        .size(width = 5.dp, height = 11.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(
                                            if (isSelected) Color.White else manggarColor
                                        )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = level.gradeLevel,
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = level.tkuTitle,
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal,
                            color = if (isSelected) Color.White.copy(alpha = 0.9f) else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
