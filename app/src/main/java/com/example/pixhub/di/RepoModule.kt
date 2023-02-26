package com.example.pixhub.di

import com.example.pixhub.data.remote.PexelsApi
import com.example.pixhub.data.remote.PixabayApi
import com.example.pixhub.data.remote.UnsplashApi
import com.example.pixhub.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideUnsplashRepo(
        api: UnsplashApi
    ) : UnsplashRepository = UnsplashRepoImpl(api)

    @Provides
    @Singleton
    fun providePexelsRepo(
        api: PexelsApi
    ) : PexelsRepository = PexelsRepoImpl(api)

    @Provides
    @Singleton
    fun providePixabayRepo(
        api: PixabayApi
    ) : PixabayRepository = PixabayRepoImpl(api)
}