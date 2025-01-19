package com.example.graphql.data

import com.apollographql.apollo.ApolloClient
import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.graphql.domain.CountryClient
import com.example.graphql.domain.DetailedCountry
import com.example.graphql.domain.SimpleCountry
import com.example.graphql.domain.asDomainModel
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