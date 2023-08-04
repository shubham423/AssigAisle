package com.example.assigaisle.di

import com.example.assigaisle.data.network.ApiService
import com.example.assigaisle.data.repository.DataRepositoryImpl
import com.example.assigaisle.domain.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideWeatherRepository(
        apiService: ApiService
    ): DataRepository = DataRepositoryImpl(apiService)
}