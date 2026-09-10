package com.example.data.source

import com.example.data.model.ScoutProfileEntity
import com.example.data.model.SkuArea
import com.example.data.model.SkuItemEntity
import com.example.data.model.SkuLevel

object InitialSkuData {

    val defaultProfile = ScoutProfileEntity(
        id = 1,
        fullName = "Siswa Pramuka Margawangi",
        gradeClass = "Kelas 4 SD",
        reguName = "Regu Rajawali (Putra 06005)",
        pangkalan = "SD NEGRI MARGAWANGI",
        gudep = "GUDEP 06005 DAN 06006",
        pembinaName = "Kak Pembina SD Negri Margawangi"
    )

    fun getInitialSkuItems(): List<SkuItemEntity> {
        val items = mutableListOf<SkuItemEntity>()

        // =========================================================================
        // SD KELAS 1: SIAGA MULA (Tingkat 1 Siaga - Usia 7 Tahun) - 15 Butir SKU
        // =========================================================================
        items.add(
            SkuItemEntity(
                id = 1,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.SPIRITUAL.name,
                pointNumber = 1,
                title = "Rajin menjalankan ibadah sesuai dengan agama yang dianutnya",
                panduanPembina = "Yanda/Bunda menanyakan kebiasaan ibadah ananda di rumah dan sekolah, didukung catatan orang tua atau guru agama.",
                materi = "Ibadah harian Pramuka Siaga Mula:\n• Berdoa sebelum dan sesudah belajar, makan, dan tidur.\n• Melaksanakan ibadah wajib bersama keluarga di rumah.\n• Bersikap sopan dan tenang saat waktu ibadah tiba."
            )
        )
        items.add(
            SkuItemEntity(
                id = 2,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.SPIRITUAL.name,
                pointNumber = 2,
                title = "Hafal bacaan doa sebelum dan sesudah makan serta doa sebelum tidur",
                panduanPembina = "Minta ananda melafalkan doa makan dan doa tidur dengan lancar dan penuh khidmat.",
                materi = "Doa Harian:\n• Doa Makan: Memohon berkah atas rezeki makanan dan keselamatan dari siksa api neraka.\n• Doa Tidur: Menyerahkan diri dan memohon perlindungan Tuhan Yang Maha Pengasih selama beristirahat."
            )
        )
        items.add(
            SkuItemEntity(
                id = 3,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.EMOSIONAL.name,
                pointNumber = 3,
                title = "Hafal dan dapat mengucapkan Dwisatya dan Dwidarma Pramuka Siaga",
                panduanPembina = "Ananda berdiri tegap dengan sikap siap dan melafalkan Dwisatya dan Dwidarma tanpa teks.",
                materi = "KODE KEHORMATAN PRAMUKA SIAGA:\n\n1. DWISATYA:\nDemi kehormatanku aku berjanji akan bersungguh-sungguh:\n- Menjalankan kewajibanku terhadap Tuhan Yang Maha Esa, Negara Kesatuan Republik Indonesia dan menurut aturan keluarga.\n- Setiap hari berbuat kebaikan.\n\n2. DWIDARMA:\n- Siaga itu patuh pada ayah dan ibundanya.\n- Siaga itu berani dan tidak putus asa."
            )
        )
        items.add(
            SkuItemEntity(
                id = 4,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.EMOSIONAL.name,
                pointNumber = 4,
                title = "Selalu patuh dan hormat kepada Ayah, Bunda, dan Guru di SD Negri Margawangi",
                panduanPembina = "Amati sikap sopan santun ananda saat berbicara kepada orang tua dan bapak/ibu guru.",
                materi = "Sikap Siaga Patuh:\n• Menyapa dan mencium tangan ayah, ibu, dan guru saat bertemu.\n• Mendengarkan nasihat dengan baik dan tidak membantah dengan kasar.\n• Mengucapkan tolong, maaf, dan terima kasih."
            )
        )
        items.add(
            SkuItemEntity(
                id = 5,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 5,
                title = "Mengenal nama anggota keluarga inti (Ayah, Ibu, Kakak, Adik)",
                panduanPembina = "Tanyakan nama lengkap ayah, ibu, serta saudara kandung dan alamat rumahnya.",
                materi = "Keluarga Inti:\n• Menyebutkan nama lengkap orang tua dan saudara kandung.\n• Mengetahui alamat tempat tinggal dan nomor kontak darurat keluarga."
            )
        )
        items.add(
            SkuItemEntity(
                id = 6,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 6,
                title = "Tahu nama Pangkalan SD NEGRI MARGAWANGI dan nomor GUDEP 06005 / 06006",
                panduanPembina = "Ananda dapat menyebutkan nama sekolah dan nomor gugus depan tempat berpangkalan.",
                materi = "Identitas Pangkalan:\n• Pangkalan: SD NEGRI MARGAWANGI\n• Nomor Gudep: 06005 (Perindukan Putra) dan 06006 (Perindukan Putri)\n• Kwarran & Kwarcab tempat SD Margawangi bernaung."
            )
        )
        items.add(
            SkuItemEntity(
                id = 7,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 7,
                title = "Tahu warna Bendera Kebangsaan Republik Indonesia (Merah Putih)",
                panduanPembina = "Tanyakan arti warna bendera Merah Putih dan bagaimana cara menghormatinya.",
                materi = "Bendera Merah Putih:\n• Merah artinya berani.\n• Putih artinya suci.\n• Bersikap tegap sempurna saat menghormat bendera Sang Merah Putih."
            )
        )
        items.add(
            SkuItemEntity(
                id = 8,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 8,
                title = "Dapat menyanyikan lagu kebangsaan Indonesia Raya bait pertama",
                panduanPembina = "Ananda menyanyikan lagu Indonesia Raya dengan nada dan tempo yang tepat serta sikap sempurna.",
                materi = "Indonesia Raya ciptaan W.R. Soepratman. Dinyanyikan dengan sikap sempurna tanpa bergerak saat lagu berkumandang."
            )
        )
        items.add(
            SkuItemEntity(
                id = 9,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 9,
                title = "Dapat memberi salam Pramuka dan tahu kapan menggunakannya",
                panduanPembina = "Praktekkan salam Pramuka (Salam Biasa, Salam Hormat, dan Salam Janji).",
                materi = "Salam Pramuka:\n• Salam Biasa: Diberikan kepada sesama anggota Pramuka saat bertemu.\n• Salam Hormat: Kepada bendera merah putih, Presiden/Wapres, jenazah, dan lagu Indonesia Raya.\n• Salam Janji: Saat pengucapan janji Dwisatya/Tri Satya."
            )
        )
        items.add(
            SkuItemEntity(
                id = 10,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 10,
                title = "Dapat membuat Simpul Mati dan Simpul Hidup menggunakan seutas tali",
                panduanPembina = "Uji langsung keterampilan ananda membuat simpul mati dan simpul hidup serta menyebutkan kegunaannya.",
                materi = "Simpul Dasar Siaga:\n• Simpul Mati: Menyambung 2 tali sama besar yang kering. Kuat dan tidak licin.\n• Simpul Hidup: Mengikat benda yang mudah dilepas kembali hanya dengan menarik ujung tali."
            )
        )
        items.add(
            SkuItemEntity(
                id = 11,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 11,
                title = "Mengenal 4 arah mata angin utama (Utara, Timur, Selatan, Barat)",
                panduanPembina = "Ajak ananda berdiri menghadap matahari terbit lalu menentukan arah timur, barat, utara, dan selatan.",
                materi = "Arah Mata Angin:\n• Timur: Arah matahari terbit.\n• Barat: Arah matahari terbenam.\n• Utara: Sisi kiri saat menghadap timur.\n• Selatan: Sisi kanan saat menghadap timur."
            )
        )
        items.add(
            SkuItemEntity(
                id = 12,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 12,
                title = "Mengenal lambang Gerakan Pramuka (Bayangan Tunas Kelapa)",
                panduanPembina = "Tunjukkan gambar lambang tunas kelapa dan tanyakan siapa penciptanya.",
                materi = "Lambang Pramuka:\n• Berupa Siluet (Bayangan) Tunas Kelapa (Cikal).\n• Diciptakan oleh Kak Soenardjo Atmodipoerwo.\n• Makna: Kelapa berguna dari akar hingga daun, melambangkan Pramuka yang serba guna dan bermanfaat bagi nusa bangsa."
            )
        )
        items.add(
            SkuItemEntity(
                id = 13,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.FISIK.name,
                pointNumber = 13,
                title = "Dapat mencuci tangan dengan sabun dan air mengalir secara benar",
                panduanPembina = "Peragakan 6 langkah mencuci tangan bersih dengan sabun.",
                materi = "Langkah Cuci Tangan:\n1. Telapak tangan.\n2. Punggung tangan.\n3. Sela-sela jari.\n4. Mengunci jari-jemari.\n5. Memutar ibu jari.\n6. Menggosok ujung kuku di telapak tangan."
            )
        )
        items.add(
            SkuItemEntity(
                id = 14,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.FISIK.name,
                pointNumber = 14,
                title = "Memelihara kebersihan kuku, gigi, rambut, dan pakaian seragam Pramuka",
                panduanPembina = "Pemeriksaan langsung kerapian diri ananda saat latihan mingguan.",
                materi = "Kebersihan Diri Siaga:\n• Memotong kuku seminggu sekali.\n• Menggosok gigi minimal 2 kali sehari (pagi dan sebelum tidur).\n• Memakai seragam Pramuka yang bersih, rapi, dan memakai kacu berhasduk rapi."
            )
        )
        items.add(
            SkuItemEntity(
                id = 15,
                level = SkuLevel.SIAGA_MULA.name,
                area = SkuArea.FISIK.name,
                pointNumber = 15,
                title = "Dapat melakukan gerakan dasar PBB (Siap, Istirahat di Tempat, Hormat)",
                panduanPembina = "Uji aba-aba: Siap Grak, Istirahat di Tempat Grak, Hormat Grak, dan Tegak Grak.",
                materi = "Dasar Baris-Berbaris Siaga:\n• Sikap Siap: Tumit rapat, ujung kaki terbuka 45°, tangan menggenggam di samping paha, dada membusung.\n• Istirahat di Tempat: Kaki kiri dibuka selebar bahu, tangan kiri memegang pergelangan tangan kanan di belakang pinggang.\n• Hormat: Jari telunjuk kanan menempel di pelipis atau ujung topi/baret."
            )
        )

        // =========================================================================
        // SD KELAS 2: SIAGA BANTU (Tingkat 2 Siaga - Usia 8 Tahun) - 15 Butir SKU
        // =========================================================================
        items.add(
            SkuItemEntity(
                id = 16,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.SPIRITUAL.name,
                pointNumber = 1,
                title = "Dapat melaksanakan ibadah bersama di perindukan dan di rumah",
                panduanPembina = "Amati keikutsertaan ananda dalam doa bersama sebelum dan sesudah kegiatan latihan di SD Margawangi.",
                materi = "Melaksanakan ibadah bersama melatih kebersamaan, toleransi, serta membiasakan diri selalu bersyukur atas karunia Tuhan."
            )
        )
        items.add(
            SkuItemEntity(
                id = 17,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.SPIRITUAL.name,
                pointNumber = 2,
                title = "Hafal doa untuk kedua orang tua serta artinya",
                panduanPembina = "Ananda melafalkan doa untuk ibu dan ayah beserta artinya dengan khusyuk.",
                materi = "Doa Kedua Orang Tua:\n'Ya Tuhan, ampunilah dosaku dan dosa kedua orang tuaku, dan sayangilah mereka sebagaimana mereka menyayangiku di waktu aku masih kecil.'"
            )
        )
        items.add(
            SkuItemEntity(
                id = 18,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.EMOSIONAL.name,
                pointNumber = 3,
                title = "Menceritakan pengalaman berbuat kebaikan kepada teman atau keluarga",
                panduanPembina = "Minta ananda menceritakan 1 perbuatan baik yang dilakukan hari ini sesuai janji Dwisatya.",
                materi = "Perbuatan Kebaikan Siaga:\n• Membantu ibu merapikan tempat tidur.\n• Menolong teman yang terjatuh atau meminjamkan alat tulis.\n• Membuang sampah pada tempatnya di lingkungan sekolah."
            )
        )
        items.add(
            SkuItemEntity(
                id = 19,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.EMOSIONAL.name,
                pointNumber = 4,
                title = "Dapat mengendalikan emosi, tidak cepat marah dan tidak putus asa",
                panduanPembina = "Pengamatan selama permainan Barung di lapangan sekolah SD Negri Margawangi.",
                materi = "Dwidarma ke-2: Siaga itu berani dan tidak putus asa. Apabila kalah dalam permainan, tetap tersenyum dan memberi selamat kepada teman."
            )
        )
        items.add(
            SkuItemEntity(
                id = 20,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 5,
                title = "Mengenal nama teman-teman dalam Barungnya dan nama Pemimpin Barung (Pinrung)",
                panduanPembina = "Ananda menyebutkan nama anggota Barungnya (Barung Merah, Hijau, Kuning, dsb).",
                materi = "Organisasi Barung Siaga:\n• Satuan terkecil Siaga disebut Barung (beranggotakan 6-8 orang anak).\n• Dipimpin oleh Pemimpin Barung (Pinrung) dan Wakil Pinrung (Wapinrung).\n• Nama Barung menggunakan nama warna (Merah, Putih, Hijau, Kuning, Biru)."
            )
        )
        items.add(
            SkuItemEntity(
                id = 21,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 6,
                title = "Tahu lambang negara Garuda Pancasila dan 5 sila Pancasila",
                panduanPembina = "Ananda melafalkan teks 5 Sila Pancasila dengan hafal dan lancar.",
                materi = "Pancasila:\n1. Ketuhanan Yang Maha Esa (Bintang)\n2. Kemanusiaan yang adil dan beradab (Rantai)\n3. Persatuan Indonesia (Pohon Beringin)\n4. Kerakyatan yang dipimpin oleh hikmat kebijaksanaan dalam permusyawaratan/perwakilan (Kepala Banteng)\n5. Keadilan sosial bagi seluruh rakyat Indonesia (Padi dan Kapas)"
            )
        )
        items.add(
            SkuItemEntity(
                id = 22,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 7,
                title = "Dapat menyanyikan 2 lagu wajib nasional dan 1 lagu daerah Jawa Barat",
                panduanPembina = "Ananda menyanyikan lagu Garuda Pancasila / Satu Nusa Satu Bangsa dan lagu Sunda seperti Manuk Dadali / Tokecang.",
                materi = "Lagu Nasional: Garuda Pancasila, Satu Nusa Satu Bangsa.\nLagu Daerah Jawa Barat: Manuk Dadali, Tokecang, Halo-Halo Bandung."
            )
        )
        items.add(
            SkuItemEntity(
                id = 23,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 8,
                title = "Dapat membuat Simpul Anyam dan Simpul Pangkal dasar",
                panduanPembina = "Praktek langsung pembuatan simpul anyam untuk menyambung dua utas tali.",
                materi = "Simpul Siaga Bantu:\n• Simpul Anyam: Menyambung 2 tali tidak sama besar dalam keadaan kering.\n• Simpul Pangkal: Mengikat tali pada tongkat/tiang bendera."
            )
        )
        items.add(
            SkuItemEntity(
                id = 24,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 9,
                title = "Dapat membaca jam dinding (waktu analog dan digital)",
                panduanPembina = "Tunjukkan model jam jarum jam pendek dan panjang, minta ananda membaca pukul berapa.",
                materi = "Membaca Waktu:\n• Jarum pendek menunjukkan jam.\n• Jarum panjang menunjukkan menit.\n• Jarum tipis berjalan cepat menunjukkan detik."
            )
        )
        items.add(
            SkuItemEntity(
                id = 25,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 10,
                title = "Mengenal mata uang rupiah logam dan kertas serta gemar menabung",
                panduanPembina = "Tanyakan nilai uang pecahan rupiah dan periksa buku tabungan sederhana ananda.",
                materi = "Pecahan Rupiah: Uang logam (Rp500, Rp1.000) dan uang kertas (Rp2.000, Rp5.000, Rp10.000, Rp20.000, Rp50.000, Rp100.000).\nGemar menabung mencerminkan hidup hemat dan cermat."
            )
        )
        items.add(
            SkuItemEntity(
                id = 26,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 11,
                title = "Mengenal rambu-rambu lalu lintas penyeberangan jalan (Zebra Cross & Lampu Merah)",
                panduanPembina = "Tanyakan arti warna lampu lalu lintas dan cara menyeberang jalan yang aman.",
                materi = "Rambu Lalu Lintas:\n• Merah: Berhenti.\n• Kuning: Hati-hati / bersiap.\n• Hijau: Jalan.\n• Menyeberang di Zebra Cross dengan tengok kanan, tengok kiri, lalu tengok kanan lagi."
            )
        )
        items.add(
            SkuItemEntity(
                id = 27,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 12,
                title = "Dapat mengirim pesan rahasia menggunakan Sandi Angka sederhana (A=1, B=2, dst)",
                panduanPembina = "Beri ananda sebuah kata sandi angka untuk dipecahkan.",
                materi = "Sandi Angka:\nA=1, B=2, C=3, D=4, E=5, F=6, G=7, H=8, I=9, J=10, K=11, L=12, M=13, N=14, O=15, P=16, Q=17, R=18, S=19, T=20, U=21, V=22, W=23, X=24, Y=25, Z=26.\nContoh: PRAMUKA = 16-18-1-13-21-11-1."
            )
        )
        items.add(
            SkuItemEntity(
                id = 28,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.FISIK.name,
                pointNumber = 13,
                title = "Dapat melakukan senam kesegaran jasmani atau senam Pramuka anak",
                panduanPembina = "Ikuti gerakan senam bersama di lapangan SD Margawangi.",
                materi = "Senam memelihara kesehatan jantung, paru-paru, otot, dan kelenturan tubuh agar selalu bugar."
            )
        )
        items.add(
            SkuItemEntity(
                id = 29,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.FISIK.name,
                pointNumber = 14,
                title = "Dapat merawat luka lecet ringan menggunakan obat antiseptik dan plester",
                panduanPembina = "Peragakan cara membersihkan luka lecet dan menutupnya dengan plester P3K.",
                materi = "P3K Luka Ringan:\n1. Cuci luka dengan air bersih mengalir.\n2. Keringkan perlahan dengan kasa bersih.\n3. Beri antiseptik (povidone iodine).\n4. Tutup dengan plester steril."
            )
        )
        items.add(
            SkuItemEntity(
                id = 30,
                level = SkuLevel.SIAGA_BANTU.name,
                area = SkuArea.FISIK.name,
                pointNumber = 15,
                title = "Dapat melakukan gerakan jalan di tempat dan langkah tegap dalam baris-berbaris",
                panduanPembina = "Uji aba-aba: Jalan di Tempat Grak, dan Maju Jalan.",
                materi = "Jalan di tempat: Paha rata-rata air (90°), ujung kaki mengarah ke bawah, pandangan lurus ke depan."
            )
        )

        // =========================================================================
        // SD KELAS 3: SIAGA TATA (Tingkat 3 Siaga - Usia 9 Tahun) - 15 Butir SKU
        // =========================================================================
        items.add(
            SkuItemEntity(
                id = 31,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.SPIRITUAL.name,
                pointNumber = 1,
                title = "Dapat mengajak teman berbuat baik dan tertib beribadah",
                panduanPembina = "Tanyakan bagaimana cara ananda mengingatkan teman saat waktu ibadah tiba.",
                materi = "Menjadi teladan bagi adik-adik Siaga Mula dan Bantu dalam bertutur kata dan menjalankan kewajiban agama."
            )
        )
        items.add(
            SkuItemEntity(
                id = 32,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.SPIRITUAL.name,
                pointNumber = 2,
                title = "Mengenal nama tokoh-tokoh agama atau nabi dan rasul sesuai agamanya",
                panduanPembina = "Tanyakan kisah keteladanan salah satu nabi/tokoh agama.",
                materi = "Keteladanan tokoh agama mengajarkan kejujuran, kesabaran, cinta kasih, dan pengorbanan demi kebenaran."
            )
        )
        items.add(
            SkuItemEntity(
                id = 33,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.EMOSIONAL.name,
                pointNumber = 3,
                title = "Dapat memimpin doa pembuka atau penutup dalam latihan perindukan Siaga",
                panduanPembina = "Beri kesempatan ananda memimpin doa saat upacara pembukaan latihan.",
                materi = "Memimpin doa dengan suara lantang, sopan, dan menghormati keberagaman keyakinan teman-temannya."
            )
        )
        items.add(
            SkuItemEntity(
                id = 34,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.EMOSIONAL.name,
                pointNumber = 4,
                title = "Mampu menjadi Pemimpin Barung (Pinrung) atau memimpin kelompok belajar",
                panduanPembina = "Amati kepemimpinan ananda dalam membagi tugas di Barung.",
                materi = "Jiwa kepemimpinan Pramuka: Bertanggung jawab, adil, mau mendengar pendapat teman, dan mengayomi anggota."
            )
        )
        items.add(
            SkuItemEntity(
                id = 35,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 5,
                title = "Mengenal struktur pemerintahan desa/kelurahan dan kecamatan setempat",
                panduanPembina = "Tanyakan nama Kepala Desa / Lurah dan Camat di wilayah pangkalan SD Margawangi.",
                materi = "Struktur Pemerintahan:\n• RT (Rukun Tetangga)\n• RW (Rukun Warga)\n• Desa / Kelurahan (dipimpin Kepala Desa / Lurah)\n• Kecamatan (dipimpin Camat)"
            )
        )
        items.add(
            SkuItemEntity(
                id = 36,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 6,
                title = "Dapat memilah sampah organik dan anorganik di lingkungan sekolah",
                panduanPembina = "Ajak ananda memilah sampah kertas/plastik dan sampah sisa makanan di SD Margawangi.",
                materi = "Pilah Sampah:\n• Sampah Organik (Mudah membusuk): Daun, sisa buah/makanan. Bisa dijadikan pupuk kompos.\n• Sampah Anorganik (Sulit membusuk): Plastik, kaleng, kaca, botol. Perlu didaur ulang."
            )
        )
        items.add(
            SkuItemEntity(
                id = 37,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 7,
                title = "Mengetahui adat istiadat dan pahlawan nasional dari daerah Jawa Barat",
                panduanPembina = "Sebutkan nama pahlawan seperti Dewi Sartika, Otto Iskandardinata, KH Zainal Musthafa.",
                materi = "Pahlawan Nasional Asal Jawa Barat:\n• Dewi Sartika: Tokoh perintis pendidikan kaum wanita.\n• Otto Iskandardinata (Si Jalak Harupat): Pejuang kemerdekaan RI.\n• KH. Zainal Musthafa: Ulama pejuang dari Tasikmalaya."
            )
        )
        items.add(
            SkuItemEntity(
                id = 38,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 8,
                title = "Dapat menggunakan kompas untuk menentukan 8 penjuru mata angin",
                panduanPembina = "Praktekkan membidik arah dengan kompas bidik sederhana.",
                materi = "8 Penjuru Mata Angin:\n1. Utara (0°/360°)\n2. Timur Laut (45°)\n3. Timur (90°)\n4. Tenggara (135°)\n5. Selatan (180°)\n6. Barat Daya (225°)\n7. Barat (270°)\n8. Barat Laut (315°)"
            )
        )
        items.add(
            SkuItemEntity(
                id = 39,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 9,
                title = "Dapat membuat Simpul Jangkar dan Simpul Tiang sederhana",
                panduanPembina = "Uji langsung pembuatan simpul jangkar pada tongkat dan simpul tiang.",
                materi = "Simpul Siaga Tata:\n• Simpul Jangkar: Menambatkan tali pada pasak/tongkat atau membuat tandu darurat.\n• Simpul Tiang: Membuat jerat yang tidak akan mengecil atau menjepit leher korban/binatang."
            )
        )
        items.add(
            SkuItemEntity(
                id = 40,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 10,
                title = "Mengenal Sandi Kotak 1 atau Sandi Morse peluit dasar (E, T, I, M, S, O)",
                panduanPembina = "Peragakan bunyi peluit pendek (.) dan panjang (-) untuk huruf E (.) dan T (-).",
                materi = "Sandi Dasar Menuju Penggalang:\n• Morse: E = titik (.), T = garis (-), I = .. , M = -- , S = ... , O = ---\n• Digunakan untuk komunikasi jarak jauh menggunakan peluit atau senter."
            )
        )
        items.add(
            SkuItemEntity(
                id = 41,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 11,
                title = "Tahu sejarah singkat Bapak Pandu Sedunia (Lord Baden Powell)",
                panduanPembina = "Tanyakan siapa bapak pandu sedunia dan tanggal kelahirannya.",
                materi = "Bapak Pandu Sedunia:\n• Nama: Robert Stephenson Smyth Baden-Powell (Lord Baden-Powell of Gilwell).\n• Lahir di London, 22 Februari 1857.\n• Menulis buku legendaris 'Scouting for Boys' tahun 1908."
            )
        )
        items.add(
            SkuItemEntity(
                id = 42,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 12,
                title = "Dapat membuat hasta karya dari bahan bekas (kardus, botol plastik)",
                panduanPembina = "Periksa karya kerajinan tangan yang dibuat ananda dari barang daur ulang.",
                materi = "Hasta Karya daur ulang melatih kreativitas, ketelitian, dan kepedulian terhadap kebersihan lingkungan sekolah."
            )
        )
        items.add(
            SkuItemEntity(
                id = 43,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.FISIK.name,
                pointNumber = 13,
                title = "Dapat berenang gaya dada/bebas atau melakukan olahraga atletik lari 50 meter",
                panduanPembina = "Catat waktu tempuh lari cepat atau kemampuan berenang ananda.",
                materi = "Olahraga melatih kekuatan otot tungkai kaki, daya tahan pernapasan, serta kelincahan jasmani."
            )
        )
        items.add(
            SkuItemEntity(
                id = 44,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.FISIK.name,
                pointNumber = 14,
                title = "Dapat melakukan pertolongan pertama pada mimisan dan gigitan serangga",
                panduanPembina = "Praktekkan posisi duduk mencondongkan badan ke depan saat mimisan.",
                materi = "P3K Mimisan:\n1. Duduk tegak dan condongkan kepala sedikit ke depan (JANGAN menengadah ke belakang).\n2. Jepit cuping hidung selama 5-10 menit dengan ibu jari dan telunjuk.\n3. Kompres es di pangkal hidung."
            )
        )
        items.add(
            SkuItemEntity(
                id = 45,
                level = SkuLevel.SIAGA_TATA.name,
                area = SkuArea.FISIK.name,
                pointNumber = 15,
                title = "Mempersiapkan diri menuju kenaikan golongan ke Pramuka Penggalang (Kelas 4 SD)",
                panduanPembina = "Wawancara kesiapan ananda dilantik menjadi Siaga Tata dan bersiap naik ke Penggalang Ramu.",
                materi = "Masa Peralihan Siaga ke Penggalang:\n• Memahami perbedaan kode kehormatan dari Dwisatya ke Tri Satya.\n• Siap bergabung ke Pasukan Penggalang dan belajar mandiri mendirikan tenda."
            )
        )

        // =========================================================================
        // SD KELAS 4: PENGGALANG RAMU (Tingkat 1 Penggalang - Usia 10-11 Tahun)
        // 30 Butir Lengkap Resmi Kwarnas Gerakan Pramuka
        // =========================================================================
        items.add(
            SkuItemEntity(
                id = 101,
                level = SkuLevel.RAMU.name,
                area = SkuArea.SPIRITUAL.name,
                pointNumber = 1,
                title = "Selalu taat menjalankan ibadah agamanya secara pribadi dan berjamaah",
                panduanPembina = "Pembina mengamati ketertiban ibadah calon Penggalang Ramu di sekolah SD Margawangi atau memeriksa surat keterangan orang tua/guru agama.",
                materi = "Ibadah Agama:\n• Islam: Menjalankan shalat wajib 5 waktu, hafal rukun iman (6) dan rukun Islam (5), membaca Al-Qur'an/Juz Amma.\n• Kristen/Katolik: Doa harian, membaca Kitab Suci, kebaktian mingguan.\n• Hindu: Melaksanakan Tri Sandhya dan Panca Sradha.\n• Buddha: Puja bakti dan Pancasila Buddhis.\n• Khonghucu: Ibadah kebaktian dan kebajikan Ba De."
            )
        )
        items.add(
            SkuItemEntity(
                id = 102,
                level = SkuLevel.RAMU.name,
                area = SkuArea.SPIRITUAL.name,
                pointNumber = 2,
                title = "Mengetahui dan menghormati hari-hari besar keagamaan di Indonesia",
                panduanPembina = "Tanyakan nama-nama hari besar agama resmi di Indonesia dan wujud toleransi terhadap teman berbeda keyakinan.",
                materi = "Hari Besar Agama:\n• Islam: Idul Fitri, Idul Adha, Isra Mi'raj, Tahun Baru Hijriyah, Maulid Nabi.\n• Kristen & Katolik: Natal, Paskah, Kenaikan Isa Almasih.\n• Hindu: Nyepi, Galungan, Kuningan.\n• Buddha: Waisak, Asadha, Kathina.\n• Khonghucu: Tahun Baru Imlek, Cap Go Meh."
            )
        )
        items.add(
            SkuItemEntity(
                id = 103,
                level = SkuLevel.RAMU.name,
                area = SkuArea.EMOSIONAL.name,
                pointNumber = 3,
                title = "Dapat menghafal, memahami makna, dan mengamalkan Tri Satya Penggalang",
                panduanPembina = "Pramuka mengucapkan Tri Satya dengan sikap sempurna dan tangan kanan memberi hormat, lalu menjelaskan maksud setiap janji.",
                materi = "TRI SATYA PENGGALANG:\nDemi kehormatanku aku berjanji akan bersungguh-sungguh:\n1. Menjalankan kewajibanku terhadap Tuhan Yang Maha Esa, Negara Kesatuan Republik Indonesia dan mengamalkan Pancasila.\n2. Menolong sesama hidup dan mempersiapkan diri membangun masyarakat.\n3. Menepati Dasa Darma."
            )
        )
        items.add(
            SkuItemEntity(
                id = 104,
                level = SkuLevel.RAMU.name,
                area = SkuArea.EMOSIONAL.name,
                pointNumber = 4,
                title = "Dapat menghafal dan mengamalkan 10 Dasa Darma Pramuka dalam kehidupan sehari-hari",
                panduanPembina = "Uji hafalan 10 Dasa Darma secara acak dan minta contoh perbuatan nyata di lingkungan SD Negri Margawangi.",
                materi = "10 DASA DARMA PRAMUKA:\n1. Takwa kepada Tuhan Yang Maha Esa\n2. Cinta alam dan kasih sayang sesama manusia\n3. Patriot yang sopan dan kesatria\n4. Patuh dan suka bermusyawarah\n5. Rela menolong dan tabah\n6. Rajin, terampil, dan gembira\n7. Hemat, cermat, dan bersahaja\n8. Disiplin, berani, dan setia\n9. Bertanggung jawab dan dapat dipercaya\n10. Suci dalam pikiran, perkataan, dan perbuatan."
            )
        )
        items.add(
            SkuItemEntity(
                id = 105,
                level = SkuLevel.RAMU.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 5,
                title = "Rajin dan giat mengikuti latihan Pasukan Penggalang sekurang-kurangnya 8 kali berturut-turut",
                panduanPembina = "Periksa buku presensi kehadiran latihan mingguan di SD NEGRI MARGAWANGI GUDEP 06005/06006.",
                materi = "Kehadiran aktif membuktikan kedisiplinan dan kesiapan seorang calon Penggalang Ramu untuk dilantik."
            )
        )
        items.add(
            SkuItemEntity(
                id = 106,
                level = SkuLevel.RAMU.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 6,
                title = "Tahu struktur organisasi Pasukan Penggalang dan regu-regunya",
                panduanPembina = "Tanyakan nama-nama regu (Rajawali, Garuda, Melati, Mawar), Pinru, Wapinru, dan Pratama.",
                materi = "Struktur Pasukan Penggalang:\n• Pasukan (maksimal 32 orang) dipimpin oleh Pembina Pasukan.\n• Terdiri atas 3-4 Regu (masing-masing 6-8 orang).\n• Pemimpin Regu Utama disebut Pratama.\n• Dipimpin oleh Pemimpin Regu (Pinru) dan Wakil (Wapinru)."
            )
        )
        items.add(
            SkuItemEntity(
                id = 107,
                level = SkuLevel.RAMU.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 7,
                title = "Tahu sejarah Bendera Merah Putih dan aturan penggunaannya",
                panduanPembina = "Tanyakan ukuran perbandingan bendera (2:3), siapa yang menjahit bendera pusaka, dan ketentuan pemasangannya.",
                materi = "Sang Saka Merah Putih:\n• Dijahit oleh Ibu Fatmawati Soekarno tahun 1944.\n• Dikibarkan pertama kali saat Proklamasi 17 Agustus 1945 di Jl. Pegangsaan Timur No. 56 Jakarta.\n• Perbandingan panjang : lebar adalah 3 : 2."
            )
        )
        items.add(
            SkuItemEntity(
                id = 108,
                level = SkuLevel.RAMU.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 8,
                title = "Tahu sejarah lagu kebangsaan Indonesia Raya dan sikap saat menyanyikannya",
                panduanPembina = "Ananda menyanyikan lagu Indonesia Raya dengan berdiri tegap sempurna tanpa bergerak.",
                materi = "Indonesia Raya diciptakan oleh Wage Rudolf Soepratman dan diperdengarkan pertama kali pada Kongres Pemuda II tanggal 28 Oktober 1928 dengan biola."
            )
        )
        items.add(
            SkuItemEntity(
                id = 109,
                level = SkuLevel.RAMU.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 9,
                title = "Tahu arti lambang Negara Republik Indonesia Garuda Pancasila",
                panduanPembina = "Jelaskan arti jumlah bulu pada Garuda Pancasila (17-8-45) dan semboyan Bhinneka Tunggal Ika.",
                materi = "Garuda Pancasila:\n• Sayap: 17 helai (Tanggal kemerdekaan)\n• Ekor: 8 helai (Bulan Agustus)\n• Pangkal Ekor: 19 helai\n• Leher: 45 helai (Tahun 1945)\n• Semboyan: Bhinneka Tunggal Ika (Berbeda-beda tetapi tetap satu jua)."
            )
        )
        items.add(
            SkuItemEntity(
                id = 110,
                level = SkuLevel.RAMU.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 10,
                title = "Mengenal lambang Gerakan Pramuka dan lambang kepanduan sedunia (WOSM)",
                panduanPembina = "Gambarkan atau tunjukkan arti siluet tunas kelapa dan bunga lili berlingkar tali WOSM.",
                materi = "Lambang Pramuka & WOSM:\n• Tunas Kelapa: Diciptakan Kak Soenardjo Atmodipoerwo.\n• WOSM (World Organization of the Scout Movement): Bunga Lili berujung tiga melambangkan tiga janji pandu, dilingkari tali simpul mati melambangkan persaudaraan sedunia."
            )
        )
        items.add(
            SkuItemEntity(
                id = 111,
                level = SkuLevel.RAMU.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 11,
                title = "Dapat menggunakan dan mengirim berita dengan Sandi Morse dan Semaphore",
                panduanPembina = "Kirim pesan 1 kata menggunakan bendera Semaphore dan tiupan peluit Sandi Morse.",
                materi = "Morse & Semaphore:\n• Morse: Titik (.) dan Garis (-). A = .- , B = -... , C = -.-.\n• Semaphore: Menggunakan sepasang bendera 45x45 cm merah-kuning bersilangan diagonal."
            )
        )
        items.add(
            SkuItemEntity(
                id = 112,
                level = SkuLevel.RAMU.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 12,
                title = "Dapat membuat simpul hidup, mati, pangkal, jangkar, anyam, dan tiang",
                panduanPembina = "Uji langsung 6 simpul dasar pionering dengan seutas tali tambang pramuka.",
                materi = "6 Simpul Wajib Penggalang Ramu:\n1. Simpul Hidup (mudah dilepas)\n2. Simpul Mati (sambung 2 tali sama besar)\n3. Simpul Pangkal (ikat awal/akhir tongkat)\n4. Simpul Jangkar (tandu darurat)\n5. Simpul Anyam (sambung 2 tali beda ukuran)\n6. Simpul Tiang (jerat penyelamat tidak menjepit)."
            )
        )
        items.add(
            SkuItemEntity(
                id = 113,
                level = SkuLevel.RAMU.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 13,
                title = "Dapat menggunakan kompas bidik dan menentukan arah mata angin",
                panduanPembina = "Arahkan adik membidik sebuah pohon/gedung di SD Margawangi dan menyebutkan derajat azimuth-nya.",
                materi = "Kompas Bidik:\nLetakkan mendatar, buka penutup prisma, intip jarum utara (magnetik), dan baca sudut derajat bidikan (azimuth)."
            )
        )
        items.add(
            SkuItemEntity(
                id = 114,
                level = SkuLevel.RAMU.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 14,
                title = "Dapat membuat dan membaca tanda jejak dan tanda medan sederhana",
                panduanPembina = "Buat jejak dari ranting, batu, atau goresan tanah di rute penjelajahan sekolah.",
                materi = "Tanda Jejak:\n• Ranting patah serong: Ikuti arah ranting\n• Tumpukan batu 3: Bahaya / rintangan\n• Batu dengan panah ranting: Menuju sumber air\n• Rumput terikat: Jalan ditutup."
            )
        )
        items.add(
            SkuItemEntity(
                id = 115,
                level = SkuLevel.RAMU.name,
                area = SkuArea.FISIK.name,
                pointNumber = 15,
                title = "Dapat melakukan PBB baris berbaris dasar dengan tongkat Pramuka",
                panduanPembina = "Uji aba-aba: Sikap sempurna bertongkat, Istirahat di tempat, Hormat bertongkat, dan Lencang depan.",
                materi = "PBB Bertongkat:\nTongkat dipegang tangan kanan merapat di sisi kanan badan, ujung bawah menempel di samping kelingking sepatu kanan."
            )
        )

        // =========================================================================
        // SD KELAS 5: PENGGALANG RAKIT (Tingkat 2 Penggalang - Usia 11-12 Tahun)
        // 30 Butir Lengkap Resmi Kwarnas
        // =========================================================================
        items.add(
            SkuItemEntity(
                id = 201,
                level = SkuLevel.RAKIT.name,
                area = SkuArea.SPIRITUAL.name,
                pointNumber = 1,
                title = "Dapat menjadi imam shalat berjamaah atau memimpin doa ibadah agamanya",
                panduanPembina = "Praktekkan memimpin doa atau menjadi imam shalat rawatib di musholla SD Margawangi.",
                materi = "Syarat menjadi imam: Fasih membaca ayat suci, mengerti rukun dan syarat sah shalat, serta berakhlak mulia."
            )
        )
        items.add(
            SkuItemEntity(
                id = 202,
                level = SkuLevel.RAKIT.name,
                area = SkuArea.EMOSIONAL.name,
                pointNumber = 2,
                title = "Dapat menjelaskan makna Tri Satya dan Dasa Darma kepada adik Pramuka Siaga",
                panduanPembina = "Calon Rakit diminta menceritakan arti Tri Satya kepada adik perindukan Siaga SD Margawangi.",
                materi = "Membimbing adik kelas melatih tanggung jawab dan kemampuan komunikasi kepanduan secara santun."
            )
        )
        items.add(
            SkuItemEntity(
                id = 203,
                level = SkuLevel.RAKIT.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 3,
                title = "Pernah mengikuti perkemahan Sabtu-Minggu (Persami) atau kemah regu",
                panduanPembina = "Periksa keikutsertaan adik dalam perkemahan regu sekolah atau jambore ranting.",
                materi = "Berkemah melatih kemandirian, tidur di alam terbuka dalam tenda, memasak sendiri, dan bekerjasama dalam regu."
            )
        )
        items.add(
            SkuItemEntity(
                id = 204,
                level = SkuLevel.RAKIT.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 4,
                title = "Dapat membuat ikatan palang, ikatan silang, dan ikatan canggah (Pionering)",
                panduanPembina = "Uji pembuatan pioneering mini: tiang jemuran, rak piring, atau dragbar/tandu darurat.",
                materi = "3 Ikatan Utama Pioneering:\n• Ikatan Palang: Menyambung 2 tongkat bersilangan 90°.\n• Ikatan Silang: Menyambung 2 tongkat menyilang miring (huruf X).\n• Ikatan Canggah: Menyambung 2 tongkat lurus memanjang."
            )
        )
        items.add(
            SkuItemEntity(
                id = 205,
                level = SkuLevel.RAKIT.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 5,
                title = "Dapat membuat peta pita dan peta perjalanan pada kegiatan penjelajahan",
                panduanPembina = "Periksa hasil lembar peta pita dengan kolom: Nomor, Waktu, Jarak, Arah Kompas, dan Kiri-Kanan Medan.",
                materi = "Peta Pita:\nMembuat rekaman rute penjelajahan dari titik awal sampai akhir menggunakan mistar, busur derajat, dan kompas."
            )
        )
        items.add(
            SkuItemEntity(
                id = 206,
                level = SkuLevel.RAKIT.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 6,
                title = "Dapat menaksir tinggi pohon/tiang dan menaksir lebar sungai",
                panduanPembina = "Praktekkan menaksir tinggi tiang bendera SD Margawangi dengan rumus perbandingan segitiga (1:10).",
                materi = "Rumus Menaksir Tinggi:\nTinggi Objek = (Jarak Pengamat x Tinggi Tongkat) / Panjang Bayangan, atau metode segitiga siku-siku 45°."
            )
        )
        items.add(
            SkuItemEntity(
                id = 207,
                level = SkuLevel.RAKIT.name,
                area = SkuArea.FISIK.name,
                pointNumber = 7,
                title = "Dapat melakukan pertolongan pertama pada korban patah tulang (Balut Bidai)",
                panduanPembina = "Praktekkan pemasangan bidai kayu dan perban mitela pada lengan atau kaki yang cedera.",
                materi = "Prinsip Balut Bidai:\n• Melewati 2 sendi (sendi di atas dan di bawah tulang yang patah).\n• Ikatan erat tetapi tidak menghentikan peredaran darah."
            )
        )

        // =========================================================================
        // SD KELAS 6: PENGGALANG TERAP (Tingkat 3 Penggalang - Usia 12-13 Tahun)
        // 30 Butir Lengkap Resmi Kwarnas
        // =========================================================================
        items.add(
            SkuItemEntity(
                id = 301,
                level = SkuLevel.TERAP.name,
                area = SkuArea.SPIRITUAL.name,
                pointNumber = 1,
                title = "Selalu menjadi teladan dalam ketakwaan dan ibadah di pangkalan SD Margawangi",
                panduanPembina = "Wawancara dengan guru kelas dan guru agama mengenai keteladanan ibadah calon Penggalang Terap.",
                materi = "Penggalang Terap adalah tingkat tertinggi penggalang di SD. Menjadi teladan bagi adik-adik kelas 1 s/d 5."
            )
        )
        items.add(
            SkuItemEntity(
                id = 302,
                level = SkuLevel.TERAP.name,
                area = SkuArea.EMOSIONAL.name,
                pointNumber = 2,
                title = "Dapat mengendalikan diri, memimpin musyawarah regu, dan mengambil keputusan bijak",
                panduanPembina = "Amati kepemimpinan adik saat memimpin musyawarah Dewan Penggalang.",
                materi = "Musyawarah mufakat menjunjung tinggi persaudaraan dan keadilan sesuai Sila ke-4 Pancasila."
            )
        )
        items.add(
            SkuItemEntity(
                id = 303,
                level = SkuLevel.TERAP.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 3,
                title = "Dapat membuat menara pandang atau jembatan darurat (Pioneering Kompleks)",
                panduanPembina = "Uji pembuatan menara kaki 3 atau jembatan monyet bersama anggota regunya.",
                materi = "Pioneering Menara Kaki Tiga membutuhkan simpul kaki tiga, ikatan palang penguat, dan tali pengaman yang kokoh."
            )
        )
        items.add(
            SkuItemEntity(
                id = 304,
                level = SkuLevel.TERAP.name,
                area = SkuArea.INTELEKTUAL.name,
                pointNumber = 4,
                title = "Mahir berkomunikasi sandi Morse kecepatan sedang dan Semaphore jarak jauh",
                panduanPembina = "Uji penerimaan sandi Morse peluit/lampu dan Semaphore dengan pesan sepanjang 3 kalimat.",
                materi = "Kecepatan dan ketepatan membaca huruf tanpa jeda ragu-ragu membuktikan kecakapan tingkat Terap."
            )
        )
        items.add(
            SkuItemEntity(
                id = 305,
                level = SkuLevel.TERAP.name,
                area = SkuArea.FISIK.name,
                pointNumber = 5,
                title = "Dapat melakukan teknik evakuasi korban kecelakaan (Resusitasi Jantung Paru & Tandu)",
                panduanPembina = "Praktekkan posisi pemulihan (recovery position) dan pembuatan tandu darurat cepat.",
                materi = "Evakuasi Korban: Cek respon (AVPU), bersihkan jalan napas, amankan tulang leher, angkat dengan tandu secara serempak."
            )
        )
        items.add(
            SkuItemEntity(
                id = 306,
                level = SkuLevel.TERAP.name,
                area = SkuArea.SOSIAL.name,
                pointNumber = 6,
                title = "Mempersiapkan diri menuju Pramuka Garuda Penggalang dan Penegak (SMP)",
                panduanPembina = "Periksa kelengkapan portofolio SKU Terap dan SKK (Syarat Kecakapan Khusus) untuk uji Pramuka Garuda.",
                materi = "Pramuka Garuda adalah tingkatan kecakapan tertinggi yang dapat dicapai seorang pramuka dengan dedikasi teladan."
            )
        )

        return items
    }
}
