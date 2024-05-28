package com.plcoding.mycashtask.yjahz.presentation.auth

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import com.plcoding.mycashtask.yjahz.presentation.MainActivity
import com.plcoding.mycashtask.yjahz.presentation.util.Screen
import com.plcoding.mycashtask.ui.theme.CleanArchitectureNoteAppTheme
import com.plcoding.mycashtask.Extension.clearAndSetContent
import com.plcoding.mycashtask.R
import com.plcoding.mycashtask.yjahz.presentation.home.HomeScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@LargeTest
class AuthScreenEndToEndTest{

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalAnimationApi::class)
    @Before
    fun setup(){
        hiltRule.inject()

        composeRule.clearAndSetContent {

            CleanArchitectureNoteAppTheme {
                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()

                Scaffold(
                    scaffoldState = scaffoldState
                ) { _ ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AuthScreen.route
                    ) {
                        composable(route = Screen.AuthScreen.route) {
                            AuthScreen(navController)
                        }
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }

    @Test
    fun assertIfNameFieldExist() = runTest {
        composeRule.onNodeWithText("Name").assertIsDisplayed()
    }

    @Test
    fun assertIfEmailFieldExist() = runTest {
        composeRule.onNodeWithText("Email").assertIsDisplayed()
    }

    @Test
    fun assertIfPhoneFieldExist() = runTest {
        composeRule.onNodeWithText("Phone").assertIsDisplayed()
    }

    @Test
    fun assertIfPasswordFieldExist() = runTest {
        composeRule.onNodeWithText("Password").assertIsDisplayed()
    }

    @Test
    fun assertIfSignUpFieldExist() = runTest {
        composeRule.onNodeWithText("Sign Up").assertIsDisplayed()
    }

    @Test
    fun assertSignUpClickNavigateToHomeScreen(){
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.name_hint)).performTextInput("Ali")
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.email_hint)).performTextInput("ali24@live.com")
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.phone_hint)).performTextInput("01142004183")
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.password_hint)).performTextInput("12345678")
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.confirm_hint)).performTextInput("12345678")

        composeRule.onNodeWithText(composeRule.activity.getString(R.string.signup)).performClick()

    }


}