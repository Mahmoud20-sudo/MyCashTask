package com.plcoding.mycashtask.yjahz.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plcoding.mycashtask.R
import com.plcoding.mycashtask.yjahz.presentation.home.components.BasicTextField
import com.plcoding.mycashtask.yjahz.util.Constants.LOGIN_SWITCH_TAG

@Composable
fun RegisterLayout(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginCallBack: () -> Unit
) {

    Spacer(modifier = Modifier.height(20.dp))

    BasicTextField(
        title = stringResource(id = R.string.name),
        hint = stringResource(id = R.string.name_hint),
        input = viewModel._name,
        maxLength = 14,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp)
    ) { name ->
        viewModel.setName(name)
    }

    BasicTextField(
        title = stringResource(id = R.string.email),
        hint = stringResource(id = R.string.email_hint),
        input = viewModel._email,
        keyboard = KeyboardType.Email,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp)
    ) { email ->
        viewModel.setEmail(email)
    }

    BasicTextField(
        title = stringResource(id = R.string.phone),
        hint = stringResource(id = R.string.phone_hint),
        input = viewModel._phone,
        maxLength = 11,
        keyboard = KeyboardType.Phone,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp)
    ) { phone ->
        viewModel.setPhone(phone)
    }

    BasicTextField(
        title = stringResource(id = R.string.password),
        hint = stringResource(id = R.string.password_hint),
        input = viewModel._password,
        maxLength = 320,
        keyboard = KeyboardType.Password,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp)
    ) { password ->
        viewModel.setPassword(password)
    }

    BasicTextField(
        title = stringResource(id = R.string.confirm),
        hint = stringResource(id = R.string.confirm_hint),
        input = viewModel._confirmPass,
        maxLength = 320,
        keyboard = KeyboardType.Password,
        imeAction = ImeAction.Done,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 10.dp)
    ) { password ->
        viewModel.setConfirmPassword(password)
    }

    TextButton(
        modifier = Modifier
            .padding(top = 30.dp, start = 50.dp, end = 50.dp)
            .fillMaxWidth()
            .height(51.dp)
            .background(
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFF00957B)
            ),
        onClick = {
            viewModel.register(
                viewModel._name,
                viewModel._email,
                viewModel._phone,
                viewModel._password,
                viewModel._confirmPass
            )
        }) {
        Text(
            text = stringResource(id = R.string.signup),
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.have_account),
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = Color(0xFF484848),
                textAlign = TextAlign.Center
            )
        )

        TextButton(
            onClick = { onLoginCallBack.invoke() },
            Modifier.testTag(LOGIN_SWITCH_TAG)
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color(0xFFBA935C),
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}