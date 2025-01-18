package com.example.qraphql.domain

import com.example.CountryQuery

data class DetailedCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val currency: String,
    val languages: List<String>,
    val continent: String
)

fun CountryQuery.Country.asDomainModel() = DetailedCountry(
    code = code,
    name = name,
    emoji = emoji,
    capital = capital ?: "No capital",
    currency = currency ?: "No currency",
    languages = languages.map { it.name },
    continent = continent.name
)