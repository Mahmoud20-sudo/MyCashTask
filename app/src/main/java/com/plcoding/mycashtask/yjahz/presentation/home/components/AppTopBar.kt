package com.plcoding.mycashtask.yjahz.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plcoding.mycashtask.R

@Preview
@Composable
fun AppTopBarPreview() {
    AppTopBar()
}

@Composable
fun AppTopBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color(0x1ABBBBBB))
            .padding(start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Image(painterResource(id = R.drawable.ic_back), contentDescription = "Back")
        
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(id = R.string.home),
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Color(0xFF484848),
                textAlign = TextAlign.Center,
                lineHeight = 19.12.sp,
                letterSpacing = (-0.3).sp
            ),
        )

        Spacer(modifier = Modifier.weight(0.5f))

        Image(painterResource(id = R.drawable.ic_cart), contentDescription = "Cart")

        Image(painterResource(id = R.drawable.ic_menu), contentDescription = "Menu")

    }

}