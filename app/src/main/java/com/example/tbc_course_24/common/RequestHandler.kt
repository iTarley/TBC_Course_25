package com.example.tbc_course_24.common

import android.util.Log
import retrofit2.Response
import java.lang.Exception

abstract class RequestHandler(){
    suspend fun <T> apiCall(request:suspend () -> Response<T>): Resource<T> {
        return try {
            Resource.loading(null)
            val response = request()
            if (response.isSuccessful){
                Resource.success(response.body()!!)
            }else{
                Resource.error(response.message())
            }
        }catch (e: Exception){
            Resource.error(e.message ?: "error")
        }
    }
}