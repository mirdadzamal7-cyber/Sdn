package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.model.ScoutGolongan
import com.example.data.model.ScoutProfileEntity
import com.example.data.model.SkuArea
import com.example.data.model.SkuItemEntity
import com.example.data.model.SkuLevel
import com.example.data.model.StudentEntity
import com.example.data.repository.SkuRepository
import com.example.data.source.InitialSkuData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class CompletionFilter(val label: String) {
    ALL("Semua Status"),
    COMPLETED("Sudah Lulus"),
    UNCOMPLETED("Belum Lulus")
}

enum class ActiveTab(val label: String) {
    SKU_LIST("Daftar SKU"),
    KAMUS("Kamus Pramuka"),
    PANDUAN("Pedoman Pembina")
}

class SkuViewModel(
    private val repository: SkuRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.ensureDataSeeded()
        }
    }

    val activeTab = MutableStateFlow(ActiveTab.SKU_LIST)
    val selectedLevel = MutableStateFlow(SkuLevel.RAMU)
    val selectedAreaFilter = MutableStateFlow<SkuArea?>(null)
    val searchQuery = MutableStateFlow("")
    val searchScopeAllLevels = MutableStateFlow(false)
    val completionFilter = MutableStateFlow(CompletionFilter.ALL)

    val quickSearchKeywords = listOf(
        "Morse", "Simpul", "Kompas", "Tri Satya", "Dasa Darma", "Dwisatya",
        "Pancasila", "P3K", "Pioneering", "Tunas Kelapa", "WOSM", "Margawangi"
    )

    // Active item for viewing detailed Panduan & Materi
    val selectedItemForDetail = MutableStateFlow<SkuItemEntity?>(null)
    // Active item for marking completion / examiner test input
    val selectedItemForTesting = MutableStateFlow<SkuItemEntity?>(null)

    // --- Student Database (Nama Siswa & Kelas) State ---
    val allStudents: StateFlow<List<StudentEntity>> = repository.getAllStudents()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeStudent: StateFlow<StudentEntity?> = repository.getActiveStudent()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val studentSearchQuery = MutableStateFlow("")
    val selectedStudentClassFilter = MutableStateFlow("Semua") // "Semua", "Kelas 1", "Kelas 2", "Kelas 3", "Kelas 4", "Kelas 5", "Kelas 6"
    val showAddStudentDialog = MutableStateFlow(false)
    val studentToEdit = MutableStateFlow<StudentEntity?>(null)

    val filteredStudents: StateFlow<List<StudentEntity>> = combine(
        allStudents,
        studentSearchQuery,
        selectedStudentClassFilter
    ) { students, query, classFilter ->
        val trimmed = query.trim()
        students.filter { student ->
            val matchesClass = if (classFilter == "Semua") true else {
                student.kelas.contains(classFilter, ignoreCase = true)
            }
            val matchesQuery = trimmed.isEmpty() ||
                    student.nama.contains(trimmed, ignoreCase = true) ||
                    student.kelas.contains(trimmed, ignoreCase = true) ||
                    student.nisn.contains(trimmed, ignoreCase = true) ||
                    student.reguBarung.contains(trimmed, ignoreCase = true)
            matchesClass && matchesQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val profile: StateFlow<ScoutProfileEntity> = repository.getProfile()
        .combine(MutableStateFlow(InitialSkuData.defaultProfile)) { loaded, default ->
            loaded ?: default
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            InitialSkuData.defaultProfile
        )

    val allItems = repository.getAllItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private data class FilterOptions(
        val level: SkuLevel,
        val area: SkuArea?,
        val query: String,
        val searchAll: Boolean,
        val compFilter: CompletionFilter
    )

    private val filterOptions = combine(
        selectedLevel,
        selectedAreaFilter,
        searchQuery,
        searchScopeAllLevels,
        completionFilter
    ) { level, area, query, searchAll, compFilter ->
        FilterOptions(level, area, query, searchAll, compFilter)
    }

    val filteredItems: StateFlow<List<SkuItemEntity>> = combine(
        allItems,
        filterOptions
    ) { items, filter ->
        val trimmedQuery = filter.query.trim()
        items.filter { item ->
            // Level matching: if searching across all classes, skip level filter
            val matchLevel = if (trimmedQuery.isNotEmpty() && filter.searchAll) {
                true
            } else {
                item.level.equals(filter.level.name, ignoreCase = true)
            }

            // Area matching
            val matchArea = filter.area == null || item.area.equals(filter.area.name, ignoreCase = true)

            // Search query matching across title, materi, panduanPembina, pointNumber, and area
            val matchQuery = trimmedQuery.isEmpty() ||
                    item.title.contains(trimmedQuery, ignoreCase = true) ||
                    item.materi.contains(trimmedQuery, ignoreCase = true) ||
                    item.panduanPembina.contains(trimmedQuery, ignoreCase = true) ||
                    item.pointNumber.toString() == trimmedQuery ||
                    item.area.contains(trimmedQuery, ignoreCase = true)

            // Completion status filter
            val matchComp = when (filter.compFilter) {
                CompletionFilter.ALL -> true
                CompletionFilter.COMPLETED -> item.isCompleted
                CompletionFilter.UNCOMPLETED -> !item.isCompleted
            }

            matchLevel && matchArea && matchQuery && matchComp
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val currentLevelStats: StateFlow<Pair<Int, Int>> = combine(
        allItems,
        selectedLevel
    ) { items, level ->
        val levelItems = items.filter { it.level.equals(level.name, ignoreCase = true) }
        val completed = levelItems.count { it.isCompleted }
        val total = levelItems.size
        completed to total
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0 to 0)

    val overallStats: StateFlow<Pair<Int, Int>> = allItems.combine(MutableStateFlow(Unit)) { items, _ ->
        val completed = items.count { it.isCompleted }
        val total = items.size
        completed to total
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0 to 0)

    fun selectLevel(level: SkuLevel) {
        selectedLevel.value = level
    }

    fun selectGrade(gradeIndex: Int) {
        // gradeIndex: 1 to 6
        val targetLevel = when (gradeIndex) {
            1 -> SkuLevel.SIAGA_MULA
            2 -> SkuLevel.SIAGA_BANTU
            3 -> SkuLevel.SIAGA_TATA
            4 -> SkuLevel.RAMU
            5 -> SkuLevel.RAKIT
            6 -> SkuLevel.TERAP
            else -> SkuLevel.RAMU
        }
        selectedLevel.value = targetLevel
    }

    fun selectArea(area: SkuArea?) {
        selectedAreaFilter.value = area
    }

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    fun toggleSearchScopeAllLevels() {
        searchScopeAllLevels.value = !searchScopeAllLevels.value
    }

    fun setSearchScopeAllLevels(all: Boolean) {
        searchScopeAllLevels.value = all
    }

    fun setFilterStatus(filter: CompletionFilter) {
        completionFilter.value = filter
    }

    fun openDetail(item: SkuItemEntity) {
        selectedItemForDetail.value = item
    }

    fun closeDetail() {
        selectedItemForDetail.value = null
    }

    fun openTestingDialog(item: SkuItemEntity) {
        selectedItemForTesting.value = item
    }

    fun closeTestingDialog() {
        selectedItemForTesting.value = null
    }

    fun toggleItemQuick(item: SkuItemEntity) {
        viewModelScope.launch {
            val newStatus = !item.isCompleted
            val today = if (newStatus) {
                SimpleDateFormat("dd MMM yyyy", Locale.forLanguageTag("id-ID")).format(Date())
            } else ""
            val defaultExaminer = if (newStatus) (profile.value.pembinaName.ifBlank { "Kak Pembina" }) else ""
            repository.updateCompletionStatus(
                id = item.id,
                isCompleted = newStatus,
                date = today,
                examiner = defaultExaminer,
                notes = if (newStatus) "Telah diuji dan memenuhi syarat di SD Negri Margawangi." else ""
            )
        }
    }

    fun saveTestingRecord(
        id: Int,
        isCompleted: Boolean,
        date: String,
        examiner: String,
        notes: String
    ) {
        viewModelScope.launch {
            repository.updateCompletionStatus(
                id = id,
                isCompleted = isCompleted,
                date = date,
                examiner = examiner,
                notes = notes
            )
            closeTestingDialog()
        }
    }

    fun updateProfile(
        name: String,
        gradeClass: String,
        regu: String,
        pangkalan: String,
        gudep: String,
        pembina: String
    ) {
        viewModelScope.launch {
            val updated = ScoutProfileEntity(
                id = 1,
                fullName = name,
                gradeClass = gradeClass,
                reguName = regu,
                pangkalan = pangkalan,
                gudep = gudep,
                pembinaName = pembina
            )
            repository.updateProfile(updated)
        }
    }

    // --- Student Database Operations ---

    fun setStudentSearchQuery(query: String) {
        studentSearchQuery.value = query
    }

    fun setStudentClassFilter(filter: String) {
        selectedStudentClassFilter.value = filter
    }

    fun openAddStudentDialog() {
        studentToEdit.value = null
        showAddStudentDialog.value = true
    }

    fun closeAddStudentDialog() {
        showAddStudentDialog.value = false
        studentToEdit.value = null
    }

    fun openEditStudentDialog(student: StudentEntity) {
        studentToEdit.value = student
        showAddStudentDialog.value = true
    }

    fun saveStudent(
        id: Int,
        nama: String,
        kelas: String,
        nisn: String,
        reguBarung: String,
        jenisKelamin: String,
        catatan: String,
        makeActive: Boolean
    ) {
        viewModelScope.launch {
            val tingkatSku = when {
                kelas.contains("1") -> "Siaga Mula"
                kelas.contains("2") -> "Siaga Bantu"
                kelas.contains("3") -> "Siaga Tata"
                kelas.contains("4") -> "Penggalang Ramu"
                kelas.contains("5") -> "Penggalang Rakit"
                kelas.contains("6") -> "Penggalang Terap"
                else -> "Penggalang Ramu"
            }

            if (id == 0) {
                val newStudent = StudentEntity(
                    nama = nama.trim(),
                    kelas = kelas.trim(),
                    nisn = nisn.trim(),
                    reguBarung = reguBarung.trim(),
                    jenisKelamin = jenisKelamin,
                    tingkatSku = tingkatSku,
                    isActive = makeActive,
                    catatan = catatan.trim()
                )
                val newId = repository.insertStudent(newStudent)
                if (makeActive) {
                    val inserted = newStudent.copy(id = newId.toInt())
                    selectActiveStudent(inserted)
                }
            } else {
                val existing = allStudents.value.find { it.id == id }
                val updatedStudent = StudentEntity(
                    id = id,
                    nama = nama.trim(),
                    kelas = kelas.trim(),
                    nisn = nisn.trim(),
                    reguBarung = reguBarung.trim(),
                    jenisKelamin = jenisKelamin,
                    tingkatSku = tingkatSku,
                    isActive = makeActive || (existing?.isActive == true),
                    catatan = catatan.trim()
                )
                repository.updateStudent(updatedStudent)
                if (makeActive) {
                    selectActiveStudent(updatedStudent)
                }
            }
            closeAddStudentDialog()
        }
    }

    fun deleteStudent(student: StudentEntity) {
        viewModelScope.launch {
            repository.deleteStudent(student)
            if (student.isActive) {
                val remaining = allStudents.value.filter { it.id != student.id }
                if (remaining.isNotEmpty()) {
                    selectActiveStudent(remaining.first())
                }
            }
        }
    }

    fun selectActiveStudent(student: StudentEntity) {
        viewModelScope.launch {
            repository.selectActiveStudent(student)
            val matchingLevel = when {
                student.kelas.contains("1") -> SkuLevel.SIAGA_MULA
                student.kelas.contains("2") -> SkuLevel.SIAGA_BANTU
                student.kelas.contains("3") -> SkuLevel.SIAGA_TATA
                student.kelas.contains("4") -> SkuLevel.RAMU
                student.kelas.contains("5") -> SkuLevel.RAKIT
                student.kelas.contains("6") -> SkuLevel.TERAP
                else -> SkuLevel.RAMU
            }
            selectedLevel.value = matchingLevel
        }
    }
}

class SkuViewModelFactory(private val repository: SkuRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SkuViewModel::class.java)) {
            return SkuViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
