package sguichard.receipemanager.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val count = uiState.value.groupesPolitiques.count()
                Text(
                    text = "Nombre de groupes Politiques $count"
                )

                AppButton(
                    text = "Bonjour Clic ", maxWidth = true,
                ) {

                    homeViewModel.fetchGroupesPolitiques()
                }
            }

        }

    }
    Column{
        Text(text = "Hello, HomePage")
    }
}