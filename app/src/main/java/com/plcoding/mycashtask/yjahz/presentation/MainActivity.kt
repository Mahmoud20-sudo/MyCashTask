package com.plcoding.mycashtask.yjahz.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.mycashtask.yjahz.data.model.Resource
import com.plcoding.mycashtask.yjahz.presentation.auth.AuthScreen
import com.plcoding.mycashtask.yjahz.presentation.auth.AuthViewModel
import com.plcoding.mycashtask.yjahz.presentation.home.HomeScreen
import com.plcoding.mycashtask.yjahz.presentation.model.UiEvent
import com.plcoding.mycashtask.yjahz.presentation.splash.SplashScreen
import com.plcoding.mycashtask.yjahz.presentation.util.Screen
import com.plcoding.mycashtask.ui.theme.CleanArchitectureNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            CleanArchitectureNoteAppTheme {
                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()
                val viewModel = viewModel<MainViewModel>()
                val userState = viewModel.userFlow.collectAsState(initial = null)
                val user = userState.value
                var startDestination by remember { mutableStateOf(Screen.SplashScreen.route) }

                LaunchedEffect(userState.value) {
                    startDestination =
                        if (user != null) Screen.HomeScreen.route else Screen.AuthScreen.route
                }

                Scaffold(
                    scaffoldState = scaffoldState
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ) {
                        composable(route = Screen.AuthScreen.route) {
                            AuthScreen(navController = navController)
                        }
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit) {

}