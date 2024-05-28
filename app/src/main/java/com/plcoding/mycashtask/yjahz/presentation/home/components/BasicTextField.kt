package com.plcoding.mycashtask.yjahz.presentation.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BasicTextField(
    title: String,
    input: String,
    hint: String,
    maxLength: Int = 320,
    keyboard: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
    onValueChanged: (value: String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    val masked by remember { mutableStateOf(keyboard == KeyboardType.Password) }
    val visualTransformation by remember(masked) {
        if (masked)
            mutableStateOf(PasswordVisualTransformation())
        else
            mutableStateOf(VisualTransformation.None)
    }

    val textStyle = TextStyle(
        fontSize = 16.sp,
        color = Color(0xff484848),
        lineHeight = 19.12.sp,
        letterSpacing = (-0.3).sp,
        fontWeight = FontWeight.Medium
    )

    if (title.isNotBlank())
        Text(
            text = title,
            style = textStyle,
            modifier = modifier.padding(start = 10.dp)
        )


    TextField(
        value = input,
        onValueChange = { text ->
            if (text.length > maxLength) return@TextField
            onValueChanged.invoke(text)
        },
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color(0x99C4C4C4)
                )
            )
        },
        textStyle = textStyle,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(vertical = 4.dp)
            .border(
                width = 0.3.dp, shape =
                RoundedCornerShape(15.dp),
                color = Color(0xFFB3B3B3)
            ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0x05000000),
            cursorColor = Color.Black,
            disabledLabelColor = Color(0xffd8e6ff),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboard),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }),
        singleLine = true
    )
}