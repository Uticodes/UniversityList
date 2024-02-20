package com.example.universitieslist.di

import com.example.universitieslist.data.remote.ApiService
import com.example.universitieslist.data.remote.repository.UniversityRepository
import com.example.universitieslist.data.remote.repository.UniversityRepositoryImpl
import com.example.universitieslist.dispatcher.DispatcherHelper
import com.example.universitieslist.dispatcher.DispatcherHelperImpl
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
    fun provideDispatcherHelper(): DispatcherHelper = DispatcherHelperImpl()
}
