package com.plcoding.mycashtask.yjahz.domain.use_case

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.plcoding.mycashtask.MainCoroutineRule
import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.data.model.user.User
import com.plcoding.mycashtask.yjahz.data.repository.LoginFakeRepository
import com.plcoding.mycashtask.yjahz.domain.repository.LoginRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
@ExperimentalCoroutinesApi
class LoginTest {

    private lateinit var login: Login
    private lateinit var repository: LoginFakeRepository

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repository = LoginFakeRepository()
        login = Login(repository)

        (1..3).forEach { i ->
            val user = User(
                id = i,
                email = "mahmoud$i@live.com"
            )
            repository.insertUser(user)
        }
    }

    @Test
    fun `invalid credentials, returns error`() = runTest {
        val result = login.invoke(email = "ali@live.com", password = "12345")
        assertThat(result.first().error).isNotNull()
    }

    @Test
    fun `existing users, returns data`() = runTest {
        val result = login.invoke(email = "mahmoud1@live.com", password = "12345")
        assertThat(result.first().data).isNotNull()
    }
}