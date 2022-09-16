package com.example.tbc_course_24.common

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow



abstract class BaseViewModel<T>():ViewModel() {

    protected val _flow = MutableStateFlow<Resource<T>>(Resource())
    val flow = _flow.asStateFlow()

    protected suspend fun responseHandler(
        flow: Flow<Resource<T>>,
        successCallback: ((Resource<T>) -> (Unit))?
    ){
        flow.collect(){
            when(it.status){
                Resource.Status.SUCCESS -> {
                    Log.d("testS", "${it.data}")
                    _flow.value = _flow.value.copy(
                        status = Resource.Status.SUCCESS, data = it.data)
                    successCallback?.invoke(it)
                }
                Resource.Status.ERROR -> {
                    Log.d("testE", "${it.message}")
                    _flow.value = _flow.value.copy(
                        status = Resource.Status.ERROR,
                        message = it.message)

                }
                Resource.Status.LOADING -> {
                    Log.d("testL", "${it.status}")
                    _flow.value = _flow.value.copy(
                        status = Resource.Status.LOADING)
                }
            }
        }
    }
    fun resetState() {
        _flow.value = _flow.value.copy(
            status = Resource.Status.LOADING,
            data = null,
            message = "",
        )
    }
}