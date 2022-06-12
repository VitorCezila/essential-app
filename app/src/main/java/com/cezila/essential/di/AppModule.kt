package com.cezila.essential.di

import android.app.Application
import androidx.room.Room
import com.cezila.essential.data.local.EssentialDatabase
import com.cezila.essential.data.remote.EssentialApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEssentialApi(): EssentialApi {
        return Retrofit.Builder()
            .baseUrl(EssentialApi.BASE_URL_PROD)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideEssentialDatabase(app: Application): EssentialDatabase {
        return Room.databaseBuilder(
            app,
            EssentialDatabase::class.java,
            "essential.db"
        ).build()
    }

}