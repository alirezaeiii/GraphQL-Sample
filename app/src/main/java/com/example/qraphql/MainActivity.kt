package com.example.qraphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.qraphql.presentation.CountryScreen
import com.example.qraphql.presentation.CountryViewModel
import com.example.qraphql.ui.theme.QraphQLSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QraphQLSampleTheme {
                val viewModel = hiltViewModel<CountryViewModel>()
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                CountryScreen(
                    state = state,
                    onSelectCountry = viewModel::selectCountry,
                    dismiss = viewModel::dismiss
                )
            }
        }
    }
}