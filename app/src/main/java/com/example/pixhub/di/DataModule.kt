package com.example.pixhub.di

import android.content.Context
import androidx.room.Room
import com.example.pixhub.data.local.ImageItemDB
import com.example.pixhub.data.remote.PexelsApi
import com.example.pixhub.data.remote.PixabayApi
import com.example.pixhub.data.remote.UnsplashApi
import com.example.pixhub.utils.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Singleton
    @Provides
    fun provideImageDatabase(
        @ApplicationContext context: Context
    ): ImageItemDB = Room.databaseBuilder(context, ImageItemDB::class.java,DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideImageDao(
        imageItemDB: ImageItemDB
    ) = imageItemDB.imagesDao()
    @Provides
    @Singleton
    fun provideUnsplashApi() : UnsplashApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(UNSPLASH_BASE_URL)
        .build()
        .create(UnsplashApi::class.java)


    @Provides
    @Singleton
    fun providePixabayApi() : PixabayApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(PIXABAY_BASE_URL)
        .build()
        .create(PixabayApi::class.java)

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