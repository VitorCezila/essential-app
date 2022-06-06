package com.cezila.essential.di

import com.cezila.essential.data.repository.EssentialRepositoryImpl
import com.cezila.essential.domain.repository.EssentialRepository
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
    abstract fun bindEssentialRepository(
        stockRepositoryImpl: EssentialRepositoryImpl
    ): EssentialRepository

}