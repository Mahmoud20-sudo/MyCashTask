package com.plcoding.mycashtask.yjahz.data.repository

import com.plcoding.mycashtask.di.AppModule
import com.plcoding.mycashtask.yjahz.data.api.Api
import com.plcoding.mycashtask.yjahz.data.data_source.UserDao
import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.sellers.PopularSeller
import com.plcoding.mycashtask.yjahz.domain.repository.PopularSellersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow

class PopularSellersRepositoryImpl(
    private val api: Api,
) : PopularSellersRepository {
    override suspend fun getPopularSellers(
        lat: Double,
        lng: Double
    ): Flow<Resource<PopularSeller?>> = channelFlow {
        val result = api.popularNow(lat, lng)
        if (result.isSuccessful)
            send(Resource.Success(result.body()))
        else
            send(Resource.Error(Throwable(result.errorBody()?.string())))
    }
}