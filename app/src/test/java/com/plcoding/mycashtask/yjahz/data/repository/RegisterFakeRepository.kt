package com.plcoding.mycashtask.yjahz.data.repository

import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.user.User
import com.plcoding.mycashtask.yjahz.data.model.user.UserModel
import com.plcoding.mycashtask.yjahz.domain.repository.LoginRepository
import com.plcoding.mycashtask.yjahz.domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterFakeRepository : RegisterRepository {

    var users = mutableListOf<User>()

    fun insertUser(user: User) = users.add(user)

    override suspend fun register(
        email: String,
        password: String,
        name: String,
        phone: String
    ): Flow<Resource<UserModel>> = flow {
        val checkedUser = runCatching { users.first { it.email == email } }.getOrNull()
        if (checkedUser != null)
            emit(Resource.Error(Throwable(message = "User already exist")))
        else
            emit(Resource.Success(data = UserModel(data = checkedUser)))
    }
}