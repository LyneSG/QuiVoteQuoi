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
    val isLoading: Boolean,
    val error: String? = null
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
            val result = groupePolitiqueRepositories.getGroupesPolitiques()

            result.fold(
                onSuccess = {data ->
                    _uiState.update { currentState ->
                        currentState.copy(groupesPolitiques = data, isLoading = false, error = null)
                    }
                },
                onFailure = {error ->
                    _uiState.update { currentState ->
                        currentState.copy( isLoading = false, error = error.localizedMessage)
                    }
                }
            )


        }
    }

    fun fetchGroupePolitiqueByAbbr(abbr : String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }
            val result = groupePolitiqueRepositories.getGroupePolitiqueDetail(abbr)

            result.fold(
                onSuccess = {data ->
                    _uiState.update { currentState ->
                        currentState.copy(groupesPolitiques = listOf(), groupePolitique = data, isLoading = false, error = null)
                    }
                },
                onFailure = {error ->
                    _uiState.update { currentState ->
                        currentState.copy(groupesPolitiques = listOf(), isLoading = false, error = error.localizedMessage)
                    }
                }
            )

        }
    }
}