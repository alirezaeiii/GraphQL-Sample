package com.example.graphql.domain

interface CountryClient {
    suspend fun getCountries(): Result<List<SimpleCountry>>
    suspend fun getCountry(code: String): Result<DetailedCountry>
}