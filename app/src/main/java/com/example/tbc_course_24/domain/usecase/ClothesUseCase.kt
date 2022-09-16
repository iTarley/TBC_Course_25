package com.example.tbc_course_24.domain.usecase

import com.example.tbc_course_24.domain.repository.ClothesRepository
import javax.inject.Inject

class ClothesUseCase @Inject constructor(private val clothesRepository: ClothesRepository) {

    suspend fun invoke() = clothesRepository.getClothes()

}