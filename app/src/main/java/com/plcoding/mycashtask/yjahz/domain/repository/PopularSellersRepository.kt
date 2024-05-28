package com.plcoding.mycashtask.yjahz.domain.repository

import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.sellers.PopularSeller
import kotlinx.coroutines.flow.Flow

interface PopularSellersRepository {
    suspend fun getPopularSellers(
        lat: Double, lng: Double
    ): Flow<Resource<PopularSeller?>>
}