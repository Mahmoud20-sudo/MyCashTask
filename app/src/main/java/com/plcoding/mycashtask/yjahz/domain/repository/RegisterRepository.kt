package com.plcoding.mycashtask.yjahz.domain.repository

import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.user.UserModel
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(
        email: String,
        password: String,
        name: String,
        phone: String
    ): Flow<Resource<UserModel>>
}