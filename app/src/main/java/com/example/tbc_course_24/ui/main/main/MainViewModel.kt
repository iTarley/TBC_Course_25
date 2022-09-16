package com.example.tbc_course_24.ui.main.main

import androidx.lifecycle.viewModelScope
import com.example.tbc_course_24.common.BaseViewModel
import com.example.tbc_course_24.common.Resource
import com.example.tbc_course_24.domain.model.ClothesModel
import com.example.tbc_course_24.domain.usecase.ClothesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val clothesUseCase: ClothesUseCase
) : BaseViewModel<List<ClothesModel.ClothesModelItem>>() {

    private val _clothes = MutableStateFlow(Resource<List<ClothesModel.ClothesModelItem>>())
    val clothes = _clothes.asStateFlow()


    fun getClothes(){
        viewModelScope.launch {
            resetState()
            responseHandler(clothesUseCase.invoke()){
                _clothes.value = _flow.value
            }
        }
    }





}