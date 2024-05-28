package com.plcoding.mycashtask.yjahz.data.repository

import com.plcoding.mycashtask.di.AppModule
import com.plcoding.mycashtask.yjahz.data.data_source.UserDao
import com.plcoding.mycashtask.yjahz.data.model.user.User
import com.plcoding.mycashtask.yjahz.domain.repository.ProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : ProfileRepository {

    override fun getUser(): Flow<User?> = dao.getUserProfile()
}