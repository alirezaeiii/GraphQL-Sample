package com.example.graphql.data

import android.content.Context
import com.apollographql.apollo.ApolloClient
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
class ApolloCountryClient @Inject constructor(private val apolloClient: ApolloClient,
    @ApplicationContext private val context: Context) :
    CountryClient {

    override suspend fun getCountries(): Result<List<SimpleCountry>> {
        try {
            return Result.success(
                apolloClient.query(CountriesQuery())
                    .execute().data?.countries?.map { it.asDomainModel() }
                    ?: return Result.failure(
                        CountryException(context.getString(R.string.error_message))
                    )
            )
        } catch (e: ApolloException) {
            return Result.failure(e)
        }
    }

    override suspend fun getCountry(code: String): Result<DetailedCountry> {
        try {
            return Result.success(
                apolloClient.query(CountryQuery(code)).execute().data?.country?.asDomainModel()
                    ?: return Result.failure(
                        CountryException(context.getString(R.string.error_message))
                    )
            )
        } catch (e: ApolloException) {
            return Result.failure(e)
        }
    }
}