package com.plcoding.mycashtask.yjahz.presentation.auth

import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.filters.MediumTest
import com.plcoding.mycashtask.yjahz.presentation.MainActivity
import com.plcoding.mycashtask.yjahz.presentation.util.Screen
import com.plcoding.mycashtask.ui.theme.CleanArchitectureNoteAppTheme
import com.plcoding.mycashtask.Extension.clearAndSetContent
import com.plcoding.mycashtask.waitUntilTimeout
import com.plcoding.mycashtask.yjahz.util.Constants.LOGIN_SWITCH_TAG
import com.plcoding.mycashtask.yjahz.util.Constants.SCROLLER_TAG
import com.plcoding.mycashtask.yjahz.util.Constants.SIGNUP_SWITCH_TAG
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@MediumTest
class AuthScreenTest{

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

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
    fun assertLoginClickSwitchToSignUpForm() = runTest {
        composeRule.waitForIdle()

        //lock for swipe gesture detction
        composeRule.mainClock.autoAdvance = false

        //scroll to bottom
        composeRule.onNode(hasTestTag(SCROLLER_TAG))
            .performTouchInput { swipeUp() }

        composeRule.mainClock.advanceTimeBy(ANIMATION_DURATION.toLong() + 5L) /*add 5s buffer*/

        composeRule.mainClock.autoAdvance = true

        composeRule.onNodeWithTag(LOGIN_SWITCH_TAG).performClick()

        composeRule.mainClock.advanceTimeBy(ANIMATION_DURATION.toLong() + 5L) /*add 5s buffer*/

        //check if phone field disappear
        composeRule.onNode(hasText("Phone")).assertIsNotDisplayed()

        composeRule.waitForIdle()

//        //switch back to register form
        composeRule.onNodeWithTag(SIGNUP_SWITCH_TAG).performClick()

        composeRule.waitUntilTimeout(1000)

//        //check of phone field re-appeared
        composeRule.onNode(hasText("Phone")).assertIsDisplayed()

    }
}