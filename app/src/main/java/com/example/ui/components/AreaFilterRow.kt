package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import com.example.data.model.SkuArea
import com.example.ui.CompletionFilter
import com.example.ui.theme.AreaEmosionalColor
import com.example.ui.theme.AreaFisikColor
import com.example.ui.theme.AreaIntelektualColor
import com.example.ui.theme.AreaSosialColor
import com.example.ui.theme.AreaSpiritualColor
import com.example.ui.theme.ScoutBrownPrimary
import com.example.ui.theme.ScoutRedSecondary

fun getAreaColor(areaName: String): Color {
    return when (areaName.uppercase()) {
        "SPIRITUAL" -> AreaSpiritualColor
        "EMOSIONAL" -> AreaEmosionalColor
        "SOSIAL" -> AreaSosialColor
        "INTELEKTUAL" -> AreaIntelektualColor
        "FISIK" -> AreaFisikColor
        else -> ScoutBrownPrimary
    }
}

@Composable
fun AreaFilterRow(
    selectedArea: SkuArea?,
    onAreaSelected: (SkuArea?) -> Unit,
    searchQuery: String,
    onSearchChanged: (String) -> Unit,
    searchScopeAllLevels: Boolean,
    onToggleSearchScope: (Boolean) -> Unit,
    matchCount: Int,
    quickKeywords: List<String>,
    completionFilter: CompletionFilter,
    onCompletionFilterChanged: (CompletionFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Search Input Box
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChanged,
            placeholder = {
                Text(
                    "Cari butir SKU (contoh: morse, simpul, doa, p3k)...",
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Cari Butir SKU",
                    tint = ScoutBrownPrimary,
                    modifier = Modifier.size(22.dp)
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(
                        onClick = { onSearchChanged("") },
                        modifier = Modifier.testTag("clear_search_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Hapus kata kunci pencarian",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .testTag("search_sku_input"),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ScoutBrownPrimary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f),
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )

        // Quick Search Keyword Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Kata kunci:",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            quickKeywords.forEach { keyword ->
                val isSelected = searchQuery.equals(keyword, ignoreCase = true)
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = if (isSelected) ScoutBrownPrimary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .clickable {
                            if (isSelected) onSearchChanged("") else onSearchChanged(keyword)
                        }
                        .testTag("keyword_chip_$keyword")
                ) {
                    Text(
                        text = keyword,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        // Active Search Status Banner & Scope Switch
        AnimatedVisibility(visible = searchQuery.isNotBlank()) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color(0xFFFFF8E1),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = ScoutBrownPrimary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Ditemukan $matchCount butir SKU untuk '$searchQuery'",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = ScoutBrownPrimary
                        )
                    }

                    // Scope switch: Cari di Semua Kelas SD
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .clickable { onToggleSearchScope(!searchScopeAllLevels) }
                            .padding(4.dp)
                    ) {
                        Text(
                            text = if (searchScopeAllLevels) "Semua Kelas 1-6" else "Kelas Ini",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (searchScopeAllLevels) ScoutRedSecondary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Switch(
                            checked = searchScopeAllLevels,
                            onCheckedChange = { onToggleSearchScope(it) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = ScoutRedSecondary
                            ),
                            modifier = Modifier
                                .size(width = 38.dp, height = 24.dp)
                                .testTag("toggle_search_all_levels")
                        )
                    }
                }
            }
        }

        // 5 Areas of Development
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterChip(
                selected = selectedArea == null,
                onClick = { onAreaSelected(null) },
                label = { Text("Semua Area", fontSize = 12.sp, fontWeight = FontWeight.Medium) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = ScoutBrownPrimary,
                    selectedLabelColor = Color.White
                ),
                modifier = Modifier.testTag("area_chip_all")
            )

            SkuArea.values().forEach { area ->
                val isSelected = selectedArea == area
                val chipColor = getAreaColor(area.name)
                FilterChip(
                    selected = isSelected,
                    onClick = { onAreaSelected(if (isSelected) null else area) },
                    label = {
                        Text(
                            text = area.displayName,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = chipColor,
                        selectedLabelColor = Color.White,
                        containerColor = chipColor.copy(alpha = 0.08f),
                        labelColor = chipColor
                    ),
                    modifier = Modifier.testTag("area_chip_${area.name.lowercase()}")
                )
            }
        }

        // Status Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CompletionFilter.values().forEach { filter ->
                val isSelected = completionFilter == filter
                FilterChip(
                    selected = isSelected,
                    onClick = { onCompletionFilterChanged(filter) },
                    label = { Text(filter.label, fontSize = 11.sp) },
                    shape = RoundedCornerShape(8.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.secondary,
                        selectedLabelColor = Color.White
                    ),
                    modifier = Modifier.testTag("status_filter_${filter.name.lowercase()}")
                )
            }
        }
    }
}
