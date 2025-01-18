package com.example.qraphql.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qraphql.domain.CountryClient
import com.example.qraphql.domain.DetailedCountry
import com.example.qraphql.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CountryUiState(
    val isLoading: Boolean = false,
    val countries: List<SimpleCountry> = emptyList(),
    val selectedCountry: DetailedCountry? = null
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
            _uiState.update { it.copy(countries = countryClient.getCountries(), isLoading = false) }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedCountry = countryClient.getCountry(code)) }
        }
    }

    fun dismiss() {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedCountry = null) }
        }
    }
}