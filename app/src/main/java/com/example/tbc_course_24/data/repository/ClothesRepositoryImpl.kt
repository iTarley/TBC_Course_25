package com.example.tbc_course_24.data.repository

import com.example.tbc_course_24.data.services.ApiInterface
import com.example.tbc_course_24.domain.repository.ClothesRepository
import com.example.tbc_course_24.common.RequestHandler
import com.example.tbc_course_24.common.Resource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClothesRepositoryImpl @Inject constructor (private val api: ApiInterface):
    RequestHandler(),
    ClothesRepository {


    override suspend fun getClothes() = flow {
        emit(Resource.loading())
        emit(apiCall { api.getResponse() })
    }
}

