package sguichard.receipemanager.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sguichard.receipemanager.data.GroupePolitique
import sguichard.receipemanager.repositories.GroupesPolitiquesRepository

data class HomeUIState(
    val groupesPolitiques: List<GroupePolitique>,
    val groupePolitique: GroupePolitique?,
    val isLoading: Boolean
)

class HomeViewModel: ViewModel() {

    private val groupePolitiqueRepositories = GroupesPolitiquesRepository

    private val _uiState :MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState(listOf(), null, true))
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    fun fetchGroupesPolitiques() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }
            val groupesPolitiquesTmp = groupePolitiqueRepositories.getGroupesPolitiques()

            _uiState.update { currentState ->
                currentState.copy(groupesPolitiques = groupesPolitiquesTmp, isLoading = false)
            }
        }
    }

    fun fetchGroupePolitiqueByAbbr(abbr : String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }
            val groupePolitiqueTmp = groupePolitiqueRepositories.getGroupePolitiqueDetail(abbr)
            _uiState.update { currentState ->
                currentState.copy(groupesPolitiques = listOf(), groupePolitique = groupePolitiqueTmp, isLoading = false)
            }
        }
    }
}