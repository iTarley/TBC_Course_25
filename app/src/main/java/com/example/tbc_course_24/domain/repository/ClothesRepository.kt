package com.example.tbc_course_24.domain.repository

import com.example.tbc_course_24.common.Resource
import com.example.tbc_course_24.domain.model.ClothesModel
import kotlinx.coroutines.flow.Flow

interface ClothesRepository {
        suspend fun getClothes(): Flow<Resource<List<ClothesModel.ClothesModelItem>>>
}