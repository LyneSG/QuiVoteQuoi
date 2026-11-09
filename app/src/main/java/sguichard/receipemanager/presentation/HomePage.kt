package sguichard.receipemanager.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import sguichard.receipemanager.app.AppButton
import sguichard.receipemanager.datasource.api.NetworkDataSource
import sguichard.receipemanager.presentation.viewmodels.HomeViewModel

@Composable
fun HomePage(
    homeViewModel: HomeViewModel = viewModel(),
) {
    val uiState = homeViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        homeViewModel.fetchGroupesPolitiques()
    }

    if (uiState.value.isLoading) {
        Text(
            text = "Chargement en  cours"
        )
    } else {
        Column(modifier = Modifier.fillMaxWidth()) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                uiState.value.groupesPolitiques.forEach { groupePolitique ->
                    ElevatedCard(
                        modifier = Modifier
                            .padding(10.dp, 5.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 6.dp
                        )
                    ) {
                        Row() {
                            Text(
                                text = "${groupePolitique.abbr} - ${groupePolitique.name}",
                                modifier = Modifier.padding(10.dp, 5.dp)
                            )
                        }
                        Row() {
                            Text(
                                text = "Nombre de député.es : ${groupePolitique.effectif}",
                                modifier = Modifier.padding(10.dp, 5.dp)
                            )
                        }
                    }
                }

                uiState.value.error?.let { error ->
                    Text(
                        color = Color.Red,
                        text = "Erreur : $error"
                    )
                }

                AppButton(
                    text = "Recharger groupe politique ", maxWidth = true,
                ) {
                    homeViewModel.fetchGroupesPolitiques()
                }
            }

        }

    }
}