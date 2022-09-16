package com.example.tbc_course_24.domain.model


class ClothesModel : ArrayList<ClothesModel.ClothesModelItem>(){
    data class ClothesModelItem(
        val title: String?,
        val cover: String?,
        val price: String?,
        val liked: Boolean?
    )
}