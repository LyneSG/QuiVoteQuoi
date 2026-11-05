package sguichard.receipemanager.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import sguichard.receipemanager.datasource.api.NetworkDataSource

@Composable
fun HomePage() {
    LaunchedEffect(true) {
        val response = NetworkDataSource.groupesPolitiquesService.getGroupePolitique("DR")
        println(response)
    }

    Column{
        Text(text = "Hello, HomePage")
    }
}