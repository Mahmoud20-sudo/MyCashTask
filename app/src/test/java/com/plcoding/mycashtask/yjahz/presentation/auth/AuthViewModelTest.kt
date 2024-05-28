package com.plcoding.mycashtask.yjahz.presentation.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.plcoding.mycashtask.MainCoroutineRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest
class AuthViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: AuthViewModel

    @Before
    fun setUp() {
        viewModel = AuthViewModel(null)
    }

    //====================================Login Process=================================
    /* empty name
       empty password
       password less than 8 digits
       invalid email */

    @Test
    fun `empty email, returns false`() {
        val result = viewModel.login("", "1234568910")
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password, returns false`() {
        val result = viewModel.login("mahmoud08@live.com", "")
        assertThat(result).isFalse()
    }

    @Test
    fun `short password, returns false`() {
        val result = viewModel.login("mahmoud08@live.com", "1234")
        assertThat(result).isFalse()
    }

    @Test
    fun `invalid email, returns false`() {
        val result = viewModel.login("mahmoud08live.com", "1234678910")
        assertThat(result).isFalse()
    }

    @Test
    fun `valid email and password, returns true`() {
        val result = viewModel.login("mahmoud08@live.com", "1234678910")
        assertThat(result).isTrue()
    }

    //===============================Registration Process=================================

    /*    empty name
          empty phone
          empty email
          empty password
          invalid email
          empty password
          empty confirm
          password less than 8 digits
          confirm less than 8 digits
          password and confirm not equals
    */

    @Test
    fun `empty name, returns false`() {
        val result = viewModel.register(
            name = "",
            phone = "01142004183",
            email = "mahmoud08@live.com",
            password = "12345678",
            confirm = "12345678"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty phone, returns false`() {
        val result = viewModel.register(
            name = "Mahmoud",
            phone = "",
            email = "mahmoud08@live.com",
            password = "12345678",
            confirm = "12345678"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty registerEmail, returns false`() {
        val result = viewModel.register(
            name = "Mahmoud",
            phone = "01141004183",
            email = "",
            password = "12345678",
            confirm = "12345678"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty registerPassword, returns false`() {
        val result = viewModel.register(
            name = "Mahmoud",
            phone = "01141004183",
            email = "mahmoud08@live.com",
            password = "",
            confirm = "12345678"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty confirmPassword, returns false`() {
        val result = viewModel.register(
            name = "Mahmoud",
            phone = "01141004183",
            email = "mahmoud08@live.com",
            password = "12345678",
            confirm = ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `short registerPassword, returns false`() {
        val result = viewModel.register(
            name = "Mahmoud",
            phone = "01141004183",
            email = "mahmoud08@live.com",
            password = "12345",
            confirm = "12345"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `passowrd & confirm not matches, returns false`() {
        val result = viewModel.register(
            name = "Mahmoud",
            phone = "01141004183",
            email = "mahmoud08@live.com",
            password = "1234578",
            confirm = "12345998"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid inputs, returns true`() {
        val result = viewModel.register(
            name = "Mahmoud",
            phone = "01141004183",
            email = "mahmoud08@live.com",
            password = "12345678",
            confirm = "12345678"
        )
        assertThat(result).isTrue()
    }
}