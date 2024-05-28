package com.plcoding.mycashtask.yjahz.domain.use_case

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.plcoding.mycashtask.MainCoroutineRule
import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.user.User
import com.plcoding.mycashtask.yjahz.data.repository.LoginFakeRepository
import com.plcoding.mycashtask.yjahz.data.repository.RegisterFakeRepository
import com.plcoding.mycashtask.yjahz.domain.repository.LoginRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
@ExperimentalCoroutinesApi
class RegisterTest {

    private lateinit var register: Register
    private lateinit var repository: RegisterFakeRepository

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repository = RegisterFakeRepository()
        register = Register(repository)

        (1..3).forEach { i ->
            val user = User(
                id = i,
                email = "mahmoud$i@live.com",
                name = "Ma$i",
                phone = "$i$i$i$i$i"
            )
            repository.insertUser(user)
        }
    }

    @Test
    fun `existing user, returns error`() = runTest {
        val result = register.invoke(
            email = "mahmoud1@live.com",
            name = "Ma1",
            password = "1234567",
            phone = "0111213113"
        )
        assertThat(result.first().error).isNotNull()
    }

    @Test
    fun `new users, returns success data`() = runTest {
        val result = register.invoke(
            email = "ali@live.com",
            name = "Ma1",
            password = "1234567",
            phone = "0111213113"
        )
        assertThat(result.first().data).isNotNull()
    }
}