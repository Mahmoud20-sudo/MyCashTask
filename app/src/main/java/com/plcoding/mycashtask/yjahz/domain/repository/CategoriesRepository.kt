package com.plcoding.mycashtask.yjahz.domain.repository

import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.catrgories.CategoryModel
import kotlinx.coroutines.flow.Flow

interface CategoriesRepository {
    suspend fun getCategories(): Flow<Resource<CategoryModel?>>
}