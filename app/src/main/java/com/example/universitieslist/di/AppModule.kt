package com.example.universitieslist.di

import com.example.universitieslist.remote.ApiService
import com.example.universitieslist.remote.repository.UniversityRepository
import com.example.universitieslist.remote.repository.UniversityRepositoryImpl
import com.example.universitieslist.util.DispatcherHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUniversityRepository(
        apiService: ApiService
    ): UniversityRepository = UniversityRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideDispatcherHelper(): DispatcherHelper = DispatcherHelper()
}