package com.plcoding.mycashtask.yjahz.domain.use_case

import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.sellers.PopularSeller
import com.plcoding.mycashtask.yjahz.domain.repository.PopularSellersRepository
import com.plcoding.mycashtask.yjahz.domain.repository.TrendingSellersRepository
import kotlinx.coroutines.flow.Flow

class GetTrending(
    private val repository: TrendingSellersRepository
) {
    suspend operator fun invoke(lat: Double, lng: Double): Flow<Resource<PopularSeller?>> {
        return repository.getTrendingSellers(lat, lng)
    }
}