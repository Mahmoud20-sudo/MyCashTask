package com.plcoding.mycashtask.yjahz.domain.use_case

import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.sellers.PopularSeller
import com.plcoding.mycashtask.yjahz.domain.repository.PopularSellersRepository
import kotlinx.coroutines.flow.Flow

class GetPopular(
    private val repository: PopularSellersRepository
) {
    suspend operator fun invoke(lat: Double, lng: Double): Flow<Resource<PopularSeller?>> {
        return repository.getPopularSellers(lat, lng)
    }
}