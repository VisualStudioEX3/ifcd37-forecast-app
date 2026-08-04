package com.visualstudioex3.ifcd37weatherforecast.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.visualstudioex3.ifcd37weatherforecast.ui.components.CustomizableSearchBar
import com.visualstudioex3.ifcd37weatherforecast.ui.components.HeaderTitle
import com.visualstudioex3.ifcd37weatherforecast.viewmodels.MunicipalityFinderUiState
import com.visualstudioex3.ifcd37weatherforecast.viewmodels.MunicipalityFinderViewModel
import kotlinx.serialization.Serializable

/**
 * Navigation target object for [MunicipalityFinderScreen] screen.
 */
@Serializable
object MunicipalityFinderNavigationTarget {
}

/**
 * Municipality finder screen.
 *
 * @param navController Navigation controller.
 * @param viewModel [MunicipalityFinderViewModel] view model. It's resolved by Hilt.
 */
@Composable
fun MunicipalityFinderScreen(
    navController: NavHostController,
    viewModel: MunicipalityFinderViewModel = hiltViewModel()
) {
    val uiState: MunicipalityFinderUiState by viewModel.uiState.collectAsStateWithLifecycle()
    var query: String by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderTitle("Search municipality")

        Row(Modifier.fillMaxWidth()) {
            CustomizableSearchBar(
                query,
                onQueryChange = {
                    query = it
                },
                onSearch = {
                    if (query.isNotBlank()) {
                        viewModel.find(query)
                    }
                },
                searchResults = uiState.municipalities
                    .map { municipality ->
                        "${municipality.name} (${municipality.province}, " +
                                "${municipality.autonomousCommunity})"
                    },
                onResultClick = { index, _ ->
                    Log.d("Search", uiState.municipalities[index].toString())
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Search",
                            modifier = Modifier.clickable {
                                query = ""
                            }
                        )
                    }
                },
            )
        }
    }
}
