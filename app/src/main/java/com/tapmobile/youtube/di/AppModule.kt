package com.tapmobile.youtube.di

import com.tapmobile.youtube.common.Constants
import com.tapmobile.youtube.data.YouTubeApi
import com.tapmobile.youtube.data.YouTubeRepositoryImpl
import com.tapmobile.youtube.domain.repository.YouTubeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGeckoApi(): YouTubeApi {

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val request: Request =
                chain.request()
                    .newBuilder()
                    .build()
            chain.proceed(request)
        }

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(YouTubeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideYouTubeRepository(api: YouTubeApi): YouTubeRepository {
        return YouTubeRepositoryImpl(api)
    }

}