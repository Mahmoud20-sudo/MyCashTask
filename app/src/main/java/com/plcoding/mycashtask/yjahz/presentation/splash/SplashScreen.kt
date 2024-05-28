package com.plcoding.mycashtask.yjahz.presentation.splash

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.navigation.NavController
import com.plcoding.mycashtask.yjahz.presentation.home.components.AppTopBar
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.mycashtask.R
import com.plcoding.mycashtask.yjahz.presentation.MainViewModel

@ExperimentalAnimationApi
@Composable
fun SplashScreen(
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = with(Modifier) {
            fillMaxSize()
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.background),
                    contentScale = ContentScale.FillBounds
                ).padding(top = 30.dp)
        })
    {
        Image(
            painterResource(id = R.mipmap.ic_logo),
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp),
            contentDescription = "logo",
            contentScale = ContentScale.Fit
        )
    }
}

