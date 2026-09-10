package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.FormatListBulleted
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ScoutProfileEntity
import com.example.ui.SkuViewModel
import com.example.ui.components.AreaFilterRow
import com.example.ui.components.LevelSelector
import com.example.ui.components.ScoutHeader
import com.example.ui.components.SkuDetailDialog
import com.example.ui.components.SkuItemCard
import com.example.ui.components.SkuTestingDialog
import com.example.ui.theme.ScoutBrownPrimary
import com.example.ui.theme.ScoutRedSecondary

@Composable
fun MainScoutScreen(
    viewModel: SkuViewModel,
    modifier: Modifier = Modifier
) {
    val selectedLevel by viewModel.selectedLevel.collectAsState()
    val selectedArea by viewModel.selectedAreaFilter.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchScopeAllLevels by viewModel.searchScopeAllLevels.collectAsState()
    val completionFilter by viewModel.completionFilter.collectAsState()
    val filteredItems by viewModel.filteredItems.collectAsState()

    val profile by viewModel.profile.collectAsState()
    val levelStats by viewModel.currentLevelStats.collectAsState()
    val overallStats by viewModel.overallStats.collectAsState()

    val detailItem by viewModel.selectedItemForDetail.collectAsState()
    val testingItem by viewModel.selectedItemForTesting.collectAsState()

    var showProfileDialog by remember { mutableStateOf(false) }
    var currentNavIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("main_bottom_nav"),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                NavigationBarItem(
                    selected = currentNavIndex == 0,
                    onClick = { currentNavIndex = 0 },
                    icon = {
                        Icon(
                            imageVector = if (currentNavIndex == 0) Icons.AutoMirrored.Filled.FormatListBulleted else Icons.AutoMirrored.Outlined.FormatListBulleted,
                            contentDescription = "Daftar SKU"
                        )
                    },
                    label = { Text("Daftar SKU", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = ScoutBrownPrimary,
                        indicatorColor = ScoutBrownPrimary
                    ),
                    modifier = Modifier.testTag("nav_sku_list")
                )

                NavigationBarItem(
                    selected = currentNavIndex == 1,
                    onClick = { currentNavIndex = 1 },
                    icon = {
                        Icon(
                            imageVector = if (currentNavIndex == 1) Icons.AutoMirrored.Filled.MenuBook else Icons.AutoMirrored.Outlined.MenuBook,
                            contentDescription = "Kamus & Toolkit"
                        )
                    },
                    label = { Text("Kamus Pramuka", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = ScoutBrownPrimary,
                        indicatorColor = ScoutBrownPrimary
                    ),
                    modifier = Modifier.testTag("nav_kamus")
                )

                NavigationBarItem(
                    selected = currentNavIndex == 2,
                    onClick = { currentNavIndex = 2 },
                    icon = {
                        Icon(
                            imageVector = if (currentNavIndex == 2) Icons.Filled.Info else Icons.Outlined.Info,
                            contentDescription = "Pedoman Pembina"
                        )
                    },
                    label = { Text("Pedoman Pembina", fontSize = 11.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = ScoutBrownPrimary,
                        indicatorColor = ScoutBrownPrimary
                    ),
                    modifier = Modifier.testTag("nav_panduan")
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentNavIndex) {
                0 -> {
                    // SKU Checklist Screen
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("sku_items_list"),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        item {
                            ScoutHeader(
                                profile = profile,
                                selectedLevel = selectedLevel,
                                levelStats = levelStats,
                                overallStats = overallStats,
                                onEditProfileClick = { showProfileDialog = true }
                            )
                        }

                        item {
                            LevelSelector(
                                selectedLevel = selectedLevel,
                                onLevelSelected = { viewModel.selectLevel(it) }
                            )
                        }

                        item {
                            AreaFilterRow(
                                selectedArea = selectedArea,
                                onAreaSelected = { viewModel.selectArea(it) },
                                searchQuery = searchQuery,
                                onSearchChanged = { viewModel.onSearchQueryChanged(it) },
                                searchScopeAllLevels = searchScopeAllLevels,
                                onToggleSearchScope = { viewModel.setSearchScopeAllLevels(it) },
                                matchCount = filteredItems.size,
                                quickKeywords = viewModel.quickSearchKeywords,
                                completionFilter = completionFilter,
                                onCompletionFilterChanged = { viewModel.setFilterStatus(it) }
                            )
                        }

                        if (filteredItems.isEmpty()) {
                            item {
                                EmptySkuView(
                                    query = searchQuery,
                                    onResetSearch = {
                                        viewModel.onSearchQueryChanged("")
                                        viewModel.selectArea(null)
                                        viewModel.setFilterStatus(com.example.ui.CompletionFilter.ALL)
                                        viewModel.setSearchScopeAllLevels(false)
                                    }
                                )
                            }
                        } else {
                            items(
                                items = filteredItems,
                                key = { it.id }
                            ) { item ->
                                SkuItemCard(
                                    item = item,
                                    showLevelBadge = searchQuery.isNotBlank() && searchScopeAllLevels,
                                    searchQuery = searchQuery,
                                    onDetailClick = { viewModel.openDetail(item) },
                                    onTestingClick = { viewModel.openTestingDialog(item) },
                                    onQuickToggle = { viewModel.toggleItemQuick(item) }
                                )
                            }
                        }
                    }
                }

                1 -> {
                    // Kamus & Toolkit Pramuka (Morse, Semaphore, Simpul, dll)
                    KamusPramukaScreen()
                }

                2 -> {
                    // Pedoman Pembina & Filosofi Kepramukaan SD Margawangi
                    PedomanPembinaScreen(profile = profile)
                }
            }
        }

        // Dialogs
        detailItem?.let { item ->
            SkuDetailDialog(
                item = item,
                onDismiss = { viewModel.closeDetail() },
                onOpenTesting = {
                    viewModel.closeDetail()
                    viewModel.openTestingDialog(item)
                }
            )
        }

        testingItem?.let { item ->
            SkuTestingDialog(
                item = item,
                defaultExaminer = profile.pembinaName,
                onDismiss = { viewModel.closeTestingDialog() },
                onSave = { id, isComp, date, examiner, notes ->
                    viewModel.saveTestingRecord(id, isComp, date, examiner, notes)
                }
            )
        }

        if (showProfileDialog) {
            ProfileEditDialog(
                profile = profile,
                onDismiss = { showProfileDialog = false },
                onSave = { name, gradeClass, regu, pangkalan, gudep, pembina ->
                    viewModel.updateProfile(name, gradeClass, regu, pangkalan, gudep, pembina)
                }
            )
        }
    }
}

@Composable
fun EmptySkuView(
    query: String,
    onResetSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.SearchOff,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Tidak Ada Butir SKU yang Cocok",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = if (query.isNotBlank()) "Tidak ditemukan hasil untuk '$query'. Coba kata kunci lain atau aktifkan opsi 'Semua Kelas 1-6'." else "Tidak ada butir pada filter yang dipilih.",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(12.dp))
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = ScoutBrownPrimary.copy(alpha = 0.1f),
                onClick = onResetSearch
            ) {
                Text(
                    text = "Reset Filter & Pencarian",
                    color = ScoutBrownPrimary,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun PedomanPembinaScreen(
    profile: ScoutProfileEntity,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "PANGKALAN ${profile.pangkalan}",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = ScoutBrownPrimary
                    )
                    Text(
                        text = "${profile.gudep} • Gerakan Pramuka",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = ScoutRedSecondary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Aplikasi ini memfasilitasi pembinaan dan pengujian Syarat Kecakapan Umum (SKU) resmi untuk seluruh tingkatan siswa SD Negri Margawangi dari Kelas 1 sampai Kelas 6:\n" +
                                "• Kelas 1 SD: Siaga Mula (TKU 1 Manggar Hijau)\n" +
                                "• Kelas 2 SD: Siaga Bantu (TKU 2 Manggar Hijau)\n" +
                                "• Kelas 3 SD: Siaga Tata (TKU 3 Manggar Hijau)\n" +
                                "• Kelas 4 SD: Penggalang Ramu (TKU 1 Manggar Merah)\n" +
                                "• Kelas 5 SD: Penggalang Rakit (TKU 2 Manggar Merah)\n" +
                                "• Kelas 6 SD: Penggalang Terap (TKU 3 Manggar Merah)\n",
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "PEDOMAN PENGUJIAN OLEH PEMBINA",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = ScoutBrownPrimary
                    )
                    Text(
                        text = "Petunjuk Teknis Kwarnas Gerakan Pramuka",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "1. Prinsip Pengujian SKU:\n" +
                                "• Ujian dilakukan secara perseorangan (individu), bukan massal, agar kecakapan setiap adik benar-benar terpantau.\n" +
                                "• Pembina/Guru menciptakan suasana persaudaraan yang akrab dan menyenangkan, bukan ujian yang menegangkan.\n" +
                                "• Pengujian dilakukan berangsur-angsur menurut kesiapan masing-masing anggota Pramuka.\n\n" +
                                "2. Cara Menguji:\n" +
                                "• Ujian langsung dalam praktek (tali temali, morse, kompas, hasta karya, baris-berbaris).\n" +
                                "• Pengamatan perilaku sehari-hari (ibadah, kejujuran, disiplin, kerajinan di rumah dan sekolah SD Margawangi).\n" +
                                "• Tanya jawab singkat mengenai pemahaman kiasan dasar dan lambang kepramukaan.\n\n" +
                                "3. Pelantikan & TKU:\n" +
                                "Setelah semua butir SKU pada satu tingkat diselesaikan dan ditandatangani, anggota berhak dilantik dalam Upacara Pelantikan Kenaikan Tingkat dan disematkan TKU Siaga / Penggalang.",
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "KODE KEHORMATAN PRAMUKA SD",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = ScoutRedSecondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Pramuka Siaga (Kelas 1 - 3 SD):\n" +
                                "• DWISATYA:\n" +
                                "  1. Menjalankan kewajibanku terhadap Tuhan YME, NKRI dan menurut aturan keluarga.\n" +
                                "  2. Setiap hari berbuat kebaikan.\n" +
                                "• DWIDARMA:\n" +
                                "  1. Siaga itu patuh pada ayah dan ibundanya.\n" +
                                "  2. Siaga itu berani dan tidak putus asa.\n\n" +
                                "Pramuka Penggalang (Kelas 4 - 6 SD):\n" +
                                "• TRI SATYA (3 Janji Pandu)\n" +
                                "• DASA DARMA (10 Ketentuan Moral)",
                        fontSize = 13.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
