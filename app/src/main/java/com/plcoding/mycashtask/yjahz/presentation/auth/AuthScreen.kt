package com.plcoding.mycashtask.yjahz.presentation.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.plcoding.mycashtask.R
import com.plcoding.mycashtask.yjahz.presentation.model.UiEvent
import com.plcoding.mycashtask.yjahz.presentation.util.Screen
import com.plcoding.mycashtask.yjahz.util.Constants.SCROLLER_TAG
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AuthScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val scaffoldState = rememberScaffoldState()

    val isLoading = viewModel.isLoading.collectAsState()

    var isRegisteredUser by remember { mutableStateOf(false) }
    var cardHeight by remember { mutableIntStateOf(0) }

    cardHeight =
        if (isRegisteredUser) screenHeight.div(1.6).toInt() else screenHeight.div(1.2).toInt()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is UiEvent.NavigateToNext -> {
                    navController.navigate(Screen.HomeScreen.route)
                }

                else -> {}
            }
        }
    }

    Scaffold(
        // attach snackbar host state to the scaffold
        scaffoldState = rememberScaffoldState(snackbarHostState = scaffoldState.snackbarHostState),
        content = { padingValues ->
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = with(Modifier) {
                    fillMaxSize()
                        .paint(
                            // Replace with your image id
                            painterResource(id = R.drawable.background),
                            contentScale = ContentScale.FillBounds
                        )
                        .padding(top = 30.dp)
                })
            {
                Image(
                    painterResource(id = R.mipmap.ic_logo),
                    modifier = Modifier
                        .width(146.dp)
                        .height(80.dp),
                    contentDescription = "logo"
                )

                Card(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    ),
                    backgroundColor = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(cardHeight.dp)
                ) {

                    AnimatedVisibility(visible = !isRegisteredUser) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .testTag(SCROLLER_TAG)
                        ) {
                            LabeledCard(
                                title = stringResource(id = R.string.signup).uppercase(),
                                modifier = Modifier.align(CenterHorizontally)
                            )

                            RegisterLayout(viewModel) {
                                isRegisteredUser = true
                            }
                        }
                    }

                    AnimatedVisibility(visible = isRegisteredUser) {
                        Column {

                            LabeledCard(
                                title = stringResource(id = R.string.login).uppercase(),
                                modifier = Modifier.align(CenterHorizontally)
                            )

                            LoginLayout(viewModel) {
                                isRegisteredUser = false
                            }
                        }
                    }
                }

                if (isLoading.value) {
                    Box(
                        contentAlignment = Center,
                        modifier =  Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.1f))
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(80.dp),
                            backgroundColor = Color.Green.copy(1f)
                        )
                    }
                }
            }
        } // content is not mandatory
    )

}

@Composable
fun LabeledCard(title: String, modifier: Modifier) {
    Card(
        elevation = 20.dp,
        shape = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomEnd = 40.dp,
            bottomStart = 40.dp
        ),
        modifier = modifier
            .width(160.dp)
            .height(68.dp),
        backgroundColor = Color.White
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Color(0xFF484848),
                textAlign = TextAlign.Center
            ),
            modifier = modifier
                .fillMaxSize()
                .padding(top = 20.dp, bottom = 20.dp),
        )
    }
}