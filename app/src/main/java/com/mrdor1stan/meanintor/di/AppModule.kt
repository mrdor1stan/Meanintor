package com.mrdor1stan.meanintor.di

import android.app.Application
import androidx.room.Room
import com.mrdor1stan.meanintor.FreeDictionaryApi
import com.mrdor1stan.meanintor.db.WordDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesDatabase(
        app:Application
    ) = Room.databaseBuilder(app, WordDatabase::class.java, "word-database")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providesDao(
       database:WordDatabase
    ) = database.wordDao()
}

