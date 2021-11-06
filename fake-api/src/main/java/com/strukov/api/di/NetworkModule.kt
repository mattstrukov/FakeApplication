package com.strukov.api.di

import com.strukov.api.api.FakeApi
import com.strukov.fake.api.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.FAKE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideFakeApi(retrofit: Retrofit): FakeApi = retrofit.create(FakeApi::class.java)
}