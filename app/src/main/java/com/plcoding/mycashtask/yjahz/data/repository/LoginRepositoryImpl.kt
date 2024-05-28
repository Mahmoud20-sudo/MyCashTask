package com.plcoding.mycashtask.yjahz.data.repository

import android.content.Context
import com.plcoding.mycashtask.di.AppModule
import com.plcoding.mycashtask.yjahz.data.api.Api
import com.plcoding.mycashtask.yjahz.data.data_source.UserDao
import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.user.UserModel
import com.plcoding.mycashtask.yjahz.data.util.encryptAES
import com.plcoding.mycashtask.yjahz.domain.repository.LoginRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: Api,
    private val dao: UserDao
) : LoginRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<UserModel>> = flow {
        val result = api.login(email, password)
        if (result.isSuccessful) {
            if (result.body()?.response_code == 200 && result.body()?.data?.id!! > 0) {
                val token = result.body()!!.data!!.token
                val user =
                    result.body()?.data!!.copy(token = token?.encryptAES())
                dao.saveUserProfile(user)
                emit(Resource.Success(result.body()!!))
            } else {
                emit(Resource.Error(Throwable(result.body()?.message)))
            }
        } else
            emit(Resource.Error(Throwable(result.errorBody()?.string())))

    }.flowOn(IO)
}