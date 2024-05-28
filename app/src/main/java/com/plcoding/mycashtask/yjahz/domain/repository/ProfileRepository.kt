package com.plcoding.mycashtask.yjahz.domain.repository

import com.plcoding.mycashtask.yjahz.data.model.user.User
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getUser(): Flow<User?>
}