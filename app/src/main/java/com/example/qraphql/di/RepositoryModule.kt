package com.example.qraphql.di

import com.example.qraphql.data.ApolloCountryClient
import com.example.qraphql.domain.CountryClient
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