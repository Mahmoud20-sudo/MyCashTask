package com.plcoding.mycashtask.yjahz.domain.use_case

import com.plcoding.mycashtask.yjahz.domain.repository.ProfileRepository

class GetUser(
    private val repository: ProfileRepository
) {
    operator fun invoke() = repository.getUser()
}