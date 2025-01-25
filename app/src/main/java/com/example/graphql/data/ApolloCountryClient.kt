package com.example.graphql.data

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.exception.ApolloException
import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.graphql.R
import com.example.graphql.domain.CountryClient
import com.example.graphql.domain.DetailedCountry
import com.example.graphql.domain.SimpleCountry
import com.example.graphql.domain.asDomainModel
import com.example.graphql.utils.CountryException
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApolloCountryClient @Inject constructor(
    private val apolloClient: ApolloClient,
    @ApplicationContext private val context: Context
) : CountryClient {

    override suspend fun getCountries(): Result<List<SimpleCountry>> =
        executeQuery(
            query = { apolloClient.query(CountriesQuery()).execute() },
            transform = { it.countries.map { country -> country.asDomainModel() } }
        )

    override suspend fun getCountry(code: String): Result<DetailedCountry> =
        executeQuery(
            query = { apolloClient.query(CountryQuery(code)).execute() },
            transform = {
                it.country?.asDomainModel()
                    ?: throw CountryException(context.getString(R.string.no_country))
            }
        )

    private inline fun <D : Operation.Data, T> executeQuery(
        query: () -> ApolloResponse<D>,
        transform: (D) -> T
    ): Result<T> {
        return try {
            val data = query().dataOrThrow()
            Result.success(transform(data))
        } catch (e: CountryException) {
            Result.failure(e)
        } catch (e: ApolloException) {
            Result.failure(CountryException(context.getString(R.string.error_message)))
        }
    }
}