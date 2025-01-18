package com.example.qraphql.data

import com.apollographql.apollo.ApolloClient
import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.qraphql.domain.CountryClient
import com.example.qraphql.domain.DetailedCountry
import com.example.qraphql.domain.SimpleCountry
import com.example.qraphql.domain.asDomainModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApolloCountryClient @Inject constructor(private val apolloClient: ApolloClient) :
    CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> =
        apolloClient.query(CountriesQuery()).execute().data?.countries?.map { it.asDomainModel() }
            ?: emptyList()

    override suspend fun getCountry(code: String): DetailedCountry? =
        apolloClient.query(CountryQuery(code)).execute().data?.country?.asDomainModel()
}