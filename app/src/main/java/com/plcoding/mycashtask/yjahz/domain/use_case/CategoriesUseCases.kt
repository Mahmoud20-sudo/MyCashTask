package com.plcoding.mycashtask.yjahz.domain.use_case

import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.catrgories.CategoryModel
import com.plcoding.mycashtask.yjahz.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow

class CategoriesUseCases(
    private val repository: CategoriesRepository
) {
    suspend operator fun invoke(): Flow<Resource<CategoryModel?>> {
        return repository.getCategories()
    }
}