package com.example.pixhub.di

import com.example.pixhub.data.remote.UnsplashApi
import com.example.pixhub.repository.UnsplashRepoImpl
import com.example.pixhub.repository.UnsplashRepository
import com.example.pixhub.utils.UNSPLASH_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUnsplashRepo(
        api: UnsplashApi
    ) : UnsplashRepository = UnsplashRepoImpl(api)

    @Provides
    @Singleton
    fun provideUnsplashApi() : UnsplashApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(UNSPLASH_BASE_URL)
        .build()
        .create(UnsplashApi::class.java)
}