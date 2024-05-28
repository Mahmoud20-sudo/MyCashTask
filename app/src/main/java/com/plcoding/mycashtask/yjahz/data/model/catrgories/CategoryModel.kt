package com.plcoding.mycashtask.yjahz.data.model.catrgories

data class CategoryModel(
    val `data`: List<Categories>,
    val message: String,
    val response_code: Int,
    val success: Boolean
)