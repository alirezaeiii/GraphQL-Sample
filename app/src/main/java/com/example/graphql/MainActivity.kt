package com.example.graphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.graphql.presentation.CountryScreen
import com.example.graphql.presentation.CountryViewModel
import com.example.graphql.ui.theme.GraphQLSampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraphQLSampleTheme {
                val viewModel = hiltViewModel<CountryViewModel>()
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }) { contentPadding ->
                    CountryScreen(
                        state = state,
                        onSelectCountry = viewModel::selectCountry,
                        dismiss = viewModel::dismiss,
                        modifier = Modifier.padding(contentPadding)
                    )
                    state.error?.let { error ->
                        scope.launch {
                            snackbarHostState.showSnackbar(error)
                            viewModel.onErrorShown()
                        }
                    }
                }
            }
        }
    }
}