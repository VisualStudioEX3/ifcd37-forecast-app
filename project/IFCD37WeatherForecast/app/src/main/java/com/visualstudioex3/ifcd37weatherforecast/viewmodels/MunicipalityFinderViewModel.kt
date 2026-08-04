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
 * UI state for [MunicipalityFinderViewModel] viewmodel.
 *
 * @param searching Gets if the viewmodel is performing the search process.
 * @param municipalities The result of the search.
 */
data class MunicipalityFinderUiState(
    val searching: Boolean = false,
    val municipalities: List<Municipality> = emptyList()
)

/**
 * Municipality finder viewmodel.
 *
 * @param municipalityFinder [MunicipalityFinder] service. It's resolved by Hilt.
 */
@HiltViewModel
class MunicipalityFinderViewModel @Inject constructor(
    private val municipalityFinder: MunicipalityFinder
) : ViewModel() {
    private val _uiState = MutableStateFlow(MunicipalityFinderUiState())

    /**
     * UI state for this viewmodel.
     */
    val uiState: StateFlow<MunicipalityFinderUiState> = _uiState.asStateFlow()

    /**
     * Search municipality by the given name.
     *
     * The search can return multiple coincidences.
     *
     * @param name Name of the municipality to search. The value can be a full name or part of it.
     */
    fun searchMunicipality(name: String) {
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

    /**
     * Saves the user selection.
     *
     * @param municipality [Municipality] selected from view.
     */
    fun saveSelection(municipality: Municipality) {
        // TODO: Resolve how to send this object to the next screen.
    }
}
