package com.example.graphql.domain

import com.example.CountriesQuery

data class SimpleCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String
)

fun CountriesQuery.Country.asDomainModel() = SimpleCountry(
    code = code,
    name = name,
    emoji = emoji,
    capital = capital ?: "No capital"
)
