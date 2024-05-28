package com.plcoding.mycashtask.yjahz.data.repository

import com.plcoding.mycashtask.yjahz.data.api.Api
import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.catrgories.CategoryModel
import com.plcoding.mycashtask.yjahz.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class CategoriesRepositoryImpl(
    private val api: Api
) : CategoriesRepository {
    override suspend fun getCategories(): Flow<Resource<CategoryModel?>> = channelFlow {
        val result = api.categories()
        if (result.isSuccessful) {
            send(Resource.Success(result.body()))
        } else
            send(Resource.Error(Throwable(result.errorBody()?.string())))
    }
}