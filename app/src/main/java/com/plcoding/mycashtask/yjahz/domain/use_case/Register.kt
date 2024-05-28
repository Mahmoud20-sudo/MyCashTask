package com.plcoding.mycashtask.yjahz.domain.use_case

import com.plcoding.mycashtask.yjahz.domain.repository.LoginRepository
import com.plcoding.mycashtask.yjahz.domain.repository.RegisterRepository

class Register(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        name: String,
        phone: String
    ) = repository.register(email, password, name, phone)
}