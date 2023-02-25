package com.example.pixhub.di

import com.example.pixhub.data.remote.PexelsApi
import com.example.pixhub.data.remote.UnsplashApi
import com.example.pixhub.utils.PEXELS_API_KEY
import com.example.pixhub.utils.PEXELS_BASE_URL
import com.example.pixhub.utils.UNSPLASH_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideUnsplashApi() : UnsplashApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(UNSPLASH_BASE_URL)
        .build()
        .create(UnsplashApi::class.java)

    @Provides
    @Singleton
    fun providePexelsApi(okHttpClient: OkHttpClient): PexelsApi = Retrofit.Builder()
        .baseUrl(PEXELS_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PexelsApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(PexelsApiInterceptor())
            .build()


    private class PexelsApiInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", PEXELS_API_KEY)
                .build()
            return chain.proceed(request)
        }
    }
}