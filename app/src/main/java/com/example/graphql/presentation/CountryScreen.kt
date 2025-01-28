package com.example.graphql.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.graphql.domain.DetailedCountry
import com.example.graphql.domain.SimpleCountry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CountryScreen(
    state: CountryUiState,
    onSelectCountry: (String) -> Unit,
    dismiss: () -> Unit,
    clearError: () -> Unit,
    scope: CoroutineScope = rememberCoroutineScope(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    modifier: Modifier = Modifier
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) {
        state.error?.let { error ->
            scope.launch {
                snackbarHostState.showSnackbar(error)
                clearError.invoke()
            }
        }
        Box(modifier = modifier.fillMaxSize()) {
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) { country ->
                    CountryItem(
                        country = country,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectCountry.invoke(country.code) }
                            .padding(16.dp)
                    )
                }
            }

            if (state.selectedCountry != null) {
                CountryDialog(
                    detailedCountry = state.selectedCountry,
                    dismiss = dismiss,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun CountryDialog(
    detailedCountry: DetailedCountry,
    dismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val joinedLanguage = remember { detailedCountry.languages.joinToString() }
    Dialog(onDismissRequest = dismiss) {
        Column(modifier = modifier) {
            Row {
                Text(text = detailedCountry.emoji, fontSize = 30.sp)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = detailedCountry.name, fontSize = 24.sp)
            }
            Text("Continent: " + detailedCountry.continent)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Currency: " + detailedCountry.currency)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Capital: " + detailedCountry.capital)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Language(s): $joinedLanguage")
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
private fun CountryItem(
    country: SimpleCountry,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = country.emoji, fontSize = 30.sp)
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = country.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = country.capital)
        }
    }
}