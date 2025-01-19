package com.example.graphql.di

import com.example.graphql.data.ApolloCountryClient
import com.example.graphql.domain.CountryClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCountryClient(apolloCountryClient: ApolloCountryClient): CountryClient
}