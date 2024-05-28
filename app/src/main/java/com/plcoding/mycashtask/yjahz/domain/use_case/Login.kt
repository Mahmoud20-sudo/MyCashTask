package com.plcoding.mycashtask.yjahz.domain.use_case

import com.plcoding.mycashtask.yjahz.domain.repository.LoginRepository

class Login(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}