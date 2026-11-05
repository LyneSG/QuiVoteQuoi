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
    val groupePolitique: GroupePolitique?
)

class HomeViewModel: ViewModel() {

    private val groupePolitiqueRepositories = GroupesPolitiquesRepository

    private val _uiState :MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState(listOf(), null))
    val uiState: StateFlow<HomeUIState> = _uiState.asStateFlow()

    fun fetchGroupesPolitiques() {
        viewModelScope.launch {
            val groupesPolitiquesTmp = groupePolitiqueRepositories.getGroupesPolitiques()

            _uiState.update { currentState ->
                currentState.copy(groupesPolitiques = groupesPolitiquesTmp)
            }
        }
    }

    fun fetchGroupePolitiqueByAbbr(abbr : String) {
        viewModelScope.launch {
            val groupePolitiqueTmp = groupePolitiqueRepositories.getGroupePolitiqueDetail(abbr)
            _uiState.update { currentState ->
                currentState.copy(groupesPolitiques = listOf(), groupePolitique = groupePolitiqueTmp)
            }
        }
    }
}