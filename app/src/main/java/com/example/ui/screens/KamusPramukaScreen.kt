package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AreaIntelektualColor
import com.example.ui.theme.AreaSosialColor
import com.example.ui.theme.ScoutBrownPrimary
import com.example.ui.theme.ScoutGoldTertiary
import com.example.ui.theme.ScoutRedSecondary

@Composable
fun KamusPramukaScreen(modifier: Modifier = Modifier) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Kode Kehormatan", "Sandi Morse", "Semaphore", "Simpul & Ikatan", "Kompas & Mata Angin")

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("kamus_pramuka_screen")
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            edgePadding = 16.dp,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = ScoutBrownPrimary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = {
                        Text(
                            text = title,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier.testTag("kamus_tab_$index")
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            when (selectedTab) {
                0 -> KodeKehormatanSection()
                1 -> SandiMorseSection()
                2 -> SemaphoreSection()
                3 -> SimpulIkatanSection()
                4 -> KompasSection()
            }
        }
    }
}

@Composable
fun KodeKehormatanSection() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Tri Satya
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(ScoutRedSecondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "TRI SATYA PENGGALANG",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = ScoutRedSecondary
                        )
                        Text(
                            text = "Tiga Janji Kehormatan Pandu",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Demi kehormatanku aku berjanji akan bersungguh-sungguh:\n\n" +
                            "1. Menjalankan kewajibanku terhadap Tuhan Yang Maha Esa, Negara Kesatuan Republik Indonesia dan mengamalkan Pancasila.\n\n" +
                            "2. Menolong sesama hidup dan mempersiapkan diri membangun masyarakat.\n\n" +
                            "3. Menepati Dasa Darma.",
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        // Dasa Darma
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(ScoutBrownPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(imageVector = Icons.Default.Bookmark, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "DASA DARMA PRAMUKA",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = ScoutBrownPrimary
                        )
                        Text(
                            text = "Sepuluh Ketentuan Moral Pramuka",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                val darmaList = listOf(
                    "Takwa kepada Tuhan Yang Maha Esa",
                    "Cinta alam dan kasih sayang sesama manusia",
                    "Patriot yang sopan dan kesatria",
                    "Patuh dan suka bermusyawarah",
                    "Rela menolong dan tabah",
                    "Rajin, terampil, dan gembira",
                    "Hemat, cermat, dan bersahaja",
                    "Disiplin, berani, dan setia",
                    "Bertanggung jawab dan dapat dipercaya",
                    "Suci dalam pikiran, perkataan, dan perbuatan"
                )

                darmaList.forEachIndexed { index, darma ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(ScoutBrownPrimary.copy(alpha = 0.12f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = ScoutBrownPrimary
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = darma,
                            fontSize = 13.sp,
                            lineHeight = 18.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

val morseMap = mapOf(
    'A' to ".-", 'B' to "-...", 'C' to "-.-.", 'D' to "-..", 'E' to ".",
    'F' to "..-.", 'G' to "--.", 'H' to "....", 'I' to "..", 'J' to ".---",
    'K' to "-.-", 'L' to ".-..", 'M' to "--", 'N' to "-.", 'O' to "---",
    'P' to ".--.", 'Q' to "--.-", 'R' to ".-.", 'S' to "...", 'T' to "-",
    'U' to "..-", 'V' to "...-", 'W' to ".--", 'X' to "-..-", 'Y' to "-.--",
    'Z' to "--..",
    '1' to ".----", '2' to "..---", '3' to "...--", '4' to "....-", '5' to ".....",
    '6' to "-....", '7' to "--...", '8' to "---..", '9' to "----.", '0' to "-----"
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SandiMorseSection() {
    val context = LocalContext.current
    var inputText by remember { mutableStateOf("PRAMUKA") }

    val morseResult = remember(inputText) {
        inputText.uppercase().map { char ->
            morseMap[char] ?: if (char == ' ') "/" else "?"
        }.joinToString(" ")
    }

    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        // Interactive Translator
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Penerjemah Sandi Morse Interaktif",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = ScoutBrownPrimary
                )
                Text(
                    text = "Ketik teks untuk melihat kode titik (.) dan garis (-) seketika",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    label = { Text("Teks Masukan") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("morse_input_text"),
                    shape = RoundedCornerShape(10.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFF263238),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Hasil Morse:",
                                color = Color(0xFF90A4AE),
                                fontSize = 11.sp
                            )
                            Text(
                                text = morseResult.ifBlank { "(Ketik teks di atas)" },
                                color = Color(0xFFFFD54F),
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.testTag("morse_output_text")
                            )
                        }

                        IconButton(
                            onClick = {
                                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                val clip = ClipData.newPlainText("Morse", morseResult)
                                clipboard.setPrimaryClip(clip)
                                Toast.makeText(context, "Sandi Morse disalin!", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.testTag("copy_morse_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = "Salin Morse",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }

        // Morse Alphabet Reference Chart
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Tabel Alfabet Sandi Morse (A - Z)",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = ScoutBrownPrimary
                )

                Spacer(modifier = Modifier.height(10.dp))

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    morseMap.entries.filter { it.key.isLetter() }.forEach { entry ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            modifier = Modifier.width(68.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${entry.key}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = ScoutRedSecondary
                                )
                                Text(
                                    text = entry.value,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SemaphoreSection() {
    val semaphoreItems = listOf(
        "A" to "Tangan kanan 45° bawah kanan, tangan kiri diam lurus bawah.",
        "B" to "Tangan kanan 90° mendatar kanan, tangan kiri diam lurus bawah.",
        "C" to "Tangan kanan 135° serong atas kanan, tangan kiri diam lurus bawah.",
        "D" to "Tangan kanan tegak lurus ke atas (180°), tangan kiri diam lurus bawah.",
        "E" to "Tangan kiri 45° bawah kiri, tangan kanan diam lurus bawah.",
        "F" to "Tangan kiri 90° mendatar kiri, tangan kanan diam lurus bawah.",
        "G" to "Tangan kiri 135° serong atas kiri, tangan kanan diam lurus bawah.",
        "H" to "Tangan kanan 90° mendatar kanan, tangan kiri 45° bawah kiri.",
        "I" to "Tangan kanan 135° serong atas kanan, tangan kiri 45° bawah kiri.",
        "K" to "Tangan kanan tegak lurus atas, tangan kiri 45° bawah kiri.",
        "L" to "Tangan kiri 90° mendatar kiri, tangan kanan 45° bawah kanan.",
        "M" to "Tangan kiri 90° mendatar kiri, tangan kanan 90° mendatar kanan.",
        "N" to "Tangan kiri 90° mendatar kiri, tangan kanan 135° serong atas kanan.",
        "O" to "Tangan kanan 135° serong atas kanan, tangan kiri 90° mendatar kiri.",
        "P" to "Tangan kanan tegak lurus atas, tangan kiri 90° mendatar kiri.",
        "Q" to "Tangan kanan 45° bawah kanan, tangan kiri 135° serong atas kiri.",
        "R" to "Tangan kanan 90° mendatar kanan, tangan kiri 135° serong atas kiri.",
        "S" to "Tangan kanan 135° serong atas kanan, tangan kiri 135° serong atas kiri.",
        "T" to "Tangan kanan tegak lurus atas, tangan kiri 135° serong atas kiri.",
        "U" to "Tangan kanan 135° serong atas kanan, tangan kiri tegak lurus atas."
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF57F17)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Flag, contentDescription = null, tint = Color.White)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "PANDUAN BENDERA SEMAPHORE",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = ScoutBrownPrimary
                    )
                    Text(
                        text = "Ukuran Bendera: 45 x 45 cm (Merah-Kuning Diagonal)",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            semaphoreItems.forEach { (char, desc) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(ScoutRedSecondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = desc,
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Divider(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            }
        }
    }
}

@Composable
fun SimpulIkatanSection() {
    val knots = listOf(
        Triple("Simpul Hidup", "Mengikat tiang yang mudah dibuka kembali.", "Biasa digunakan untuk mengikat hewan ternak atau tiang jemuran agar cepat dilepas."),
        Triple("Simpul Mati", "Menyambung 2 utas tali yang sama besar dan dalam keadaan kering.", "Kuat dan tidak mudah slip, sering digunakan dalam mengikat ujung tali kacu atau perban P3K."),
        Triple("Simpul Pangkal", "Mengawali dan mengakhiri suatu ikatan pada tiang/tongkat.", "Wajib dikuasai dalam pembuatan pioneering tandu dan menara."),
        Triple("Simpul Jangkar", "Membuat tandu darurat atau menambatkan perahu pada pasak/tongkat.", "Sangat praktis dan simetris di kedua sisinya."),
        Triple("Simpul Anyam", "Menyambung 2 tali tidak sama besar dan kering.", "Dapat ditingkatkan menjadi Simpul Anyam Berganda jika tali basah/licin."),
        Triple("Simpul Tiang", "Mengikat leher binatang agar tidak tercekik atau untuk menolong korban jatuh.", "Lingkaran simpul tidak akan mengecil meskipun ditarik kencang."),
        Triple("Ikatan Palang", "Mengikat 2 tongkat yang bersilangan tegak lurus (90°).", "Dimulai simpul pangkal di tongkat tegak, lilitan silang 3-4 kali, dikunci (frapping), diakhiri simpul pangkal."),
        Triple("Ikatan Silang", "Mengikat 2 tongkat bersilangan miring membentuk huruf X.", "Dimulai dengan simpul tambat atau simpul pangkal diagonal."),
        Triple("Ikatan Canggah", "Menyambung 2 tongkat bambu secara lurus memanjang.", "Digunakan untuk tiang bendera darurat yang tinggi.")
    )

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        knots.forEach { (name, func, note) ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = ScoutBrownPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Fungsi: $func",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = ScoutRedSecondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Catatan: $note",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun KompasSection() {
    val compassPoints = listOf(
        Triple("Utara (U)", "0° / 360°", "Arah jarum kompas menunjuk medan magnet bumi"),
        Triple("Utara Timur Laut (UTL)", "22.5°", "Antara Utara dan Timur Laut"),
        Triple("Timur Laut (TL)", "45°", "Arah timur serong utara"),
        Triple("Timur Menenggara (TM)", "67.5°", "Antara Timur Laut dan Timur"),
        Triple("Timur (T)", "90°", "Arah matahari terbit"),
        Triple("Timur Tenggara (TTG)", "112.5°", "Antara Timur dan Tenggara"),
        Triple("Tenggara (TG)", "135°", "Arah timur serong selatan"),
        Triple("Selatan Menenggara (SMG)", "157.5°", "Antara Tenggara dan Selatan"),
        Triple("Selatan (S)", "180°", "Lawan arah dari Utara"),
        Triple("Selatan Barat Daya (SBD)", "202.5°", "Antara Selatan dan Barat Daya"),
        Triple("Barat Daya (BD)", "225°", "Arah barat serong selatan"),
        Triple("Barat Barat Daya (BBD)", "247.5°", "Antara Barat Daya dan Barat"),
        Triple("Barat (B)", "270°", "Arah matahari terbenam"),
        Triple("Barat Barat Laut (BBL)", "292.5°", "Antara Barat dan Barat Laut"),
        Triple("Barat Laut (BL)", "315°", "Arah barat serong utara"),
        Triple("Utara Barat Laut (UBL)", "337.5°", "Antara Barat Laut dan Utara")
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF00695C)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Default.Explore, contentDescription = null, tint = Color.White)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "16 ARAH MATA ANGIN & KOMPAS",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF00695C)
                    )
                    Text(
                        text = "Derajat Azimuth untuk Navigasi Darat",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            compassPoints.forEach { (name, deg, desc) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = desc,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = ScoutBrownPrimary
                    ) {
                        Text(
                            text = deg,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
                Divider(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
            }
        }
    }
}
