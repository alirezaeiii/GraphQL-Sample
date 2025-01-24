package com.example.graphql.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphql.domain.CountryClient
import com.example.graphql.domain.DetailedCountry
import com.example.graphql.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CountryUiState(
    val isLoading: Boolean = false,
    val countries: List<SimpleCountry> = emptyList(),
    val selectedCountry: DetailedCountry? = null,
    val error: String? = null
)

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryClient: CountryClient) : ViewModel() {

    private val _uiState = MutableStateFlow(CountryUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            countryClient.getCountries().fold(
                onSuccess = { countries ->
                    _uiState.update { it.copy(countries = countries, isLoading = false) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(error = error.localizedMessage, isLoading = false)
                    }
                }
            )
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            countryClient.getCountry(code).fold(
                onSuccess = { country ->
                    _uiState.update { it.copy(selectedCountry = country) }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(error = error.localizedMessage)
                    }
                }
            )
        }
    }

    fun dismiss() {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedCountry = null) }
        }
    }
}