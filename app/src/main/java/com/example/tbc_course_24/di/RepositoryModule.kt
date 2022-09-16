package com.example.tbc_course_24.di

import com.example.tbc_course_24.data.repository.ClothesRepositoryImpl
import com.example.tbc_course_24.domain.repository.ClothesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCourseRepository(
        clothesRepositoryImpl: ClothesRepositoryImpl
    ): ClothesRepository
}