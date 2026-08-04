package com.visualstudioex3.ifcd37weatherforecast.viewmodels

import androidx.lifecycle.ViewModel
import com.visualstudioex3.application.entities.Municipality
import com.visualstudioex3.application.ports.input.MunicipalityFinder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * UI state for [MunicipalityFinderViewModel] view model.
 *
 * @param searching Sets if the view model is performing the search process.
 * @param municipalities The result of the search.
 */
data class MunicipalityFinderUiState(
    val searching: Boolean = false,
    val municipalities: List<Municipality> = emptyList()
)

/**
 * Municipality finder view model.
 */
@HiltViewModel
class MunicipalityFinderViewModel @Inject constructor(
    private val municipalityFinder: MunicipalityFinder
) : ViewModel() {
    private val _uiState = MutableStateFlow(MunicipalityFinderUiState())
    val uiState: StateFlow<MunicipalityFinderUiState> = _uiState.asStateFlow()

    fun find(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searching = true,
                municipalities = emptyList()
            )
        }

        val result: List<Municipality> = municipalityFinder.findMunicipalities(name)

        _uiState.update { currentState ->
            currentState.copy(
                searching = false,
                municipalities = result
            )
        }
    }
}
