package com.mrdor1stan.meanintor.di

import com.mrdor1stan.meanintor.FreeDictionaryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(ViewModelComponent::class)
object SearchFragmentViewModelModule {
    @Provides
    @ViewModelScoped
    fun providesWordsApi() = Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(FreeDictionaryApi::class.java)

}