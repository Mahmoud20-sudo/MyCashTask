package com.plcoding.mycashtask.yjahz.data.model.sellers

data class PopularSeller(
    val `data`: List<Seller>? = emptyList(),
    val message: String? = null,
    val response_code: Int? = null,
    val success: Boolean? = null
)