package com.example.tbc_course_24.di

import android.content.Context
import com.example.tbc_course_24.data.okhttp.OkHttpClient
import com.example.tbc_course_24.data.services.ApiInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance(@ApplicationContext context: Context): Retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/v3/")
        .client(OkHttpClient.okHttpClient(context))
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .addLast(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .build()


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)
}