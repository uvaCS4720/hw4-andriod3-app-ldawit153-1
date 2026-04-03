package edu.nd.pmcburne.hello

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class MainUIState(
    val locations: List<LocationEntity> = emptyList(),
    val selectedTag: String = "core",
    val isLoading: Boolean = true
) {
    val allTags: List<String>
        get() = locations
            .flatMap { it.tags.split(",") }
            .map { it.trim() }
            .distinct()
            .sorted()

    val filteredLocations: List<LocationEntity>
        get() = locations.filter {
            it.tags.split(",").map { t -> t.trim() }.contains(selectedTag)
        }
}

class MainViewModel(
    private val repository: LocationRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState: StateFlow<MainUIState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.syncLocations()
            val data = repository.getAllLocations()

            _uiState.value = _uiState.value.copy(
                locations = data,
                isLoading = false
            )
        }
    }

    fun updateSelectedTag(tag: String) {
        _uiState.value = _uiState.value.copy(selectedTag = tag)
    }
}

class MainViewModelFactory(
    private val repository: LocationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}