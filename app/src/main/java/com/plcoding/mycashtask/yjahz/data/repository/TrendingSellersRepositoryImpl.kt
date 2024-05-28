package com.plcoding.mycashtask.yjahz.data.repository

import com.plcoding.mycashtask.yjahz.data.api.Api
import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.sellers.PopularSeller
import com.plcoding.mycashtask.yjahz.domain.repository.TrendingSellersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow

class TrendingSellersRepositoryImpl(
    private val api: Api
) : TrendingSellersRepository {
    override suspend fun getTrendingSellers(
        lat: Double,
        lng: Double
    ): Flow<Resource<PopularSeller?>> = channelFlow {
        val result = api.trending(lat, lng)
        if (result.isSuccessful) {
            send(Resource.Success(result.body()))
        } else
            send(Resource.Error(Throwable(result.errorBody()?.string())))
    }
}