package com.example.tbc_course_24.data.services

import com.example.tbc_course_24.domain.model.ClothesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("05d71804-4628-4269-ac03-f86e9960a0bb")
    suspend fun getResponse(): Response<List<ClothesModel.ClothesModelItem>>
}