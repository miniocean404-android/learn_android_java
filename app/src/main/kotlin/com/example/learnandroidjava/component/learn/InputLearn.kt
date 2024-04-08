package com.example.learnandroidjava.component.learn

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun InputLearn() {

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isShowPassword by remember {
        mutableStateOf(false)
    }

    Column {
        TextField(
            value = username,
            onValueChange = { username = it },
            // 是否单行
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
            label = { Text("用户名") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                focusedLabelColor = Color.LightGray,
                unfocusedLabelColor = Color.Black,
                cursorColor = Color.LightGray,
            ),
            visualTransformation = VisualTransformation.None
        )

        TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Password, contentDescription = "") },
            trailingIcon = {
                Icon(
                    imageVector = if (isShowPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                    contentDescription = "",
                    modifier = Modifier.clickable {
                        isShowPassword = !isShowPassword
                    },
                    tint = Color.White
                )
            },
            label = { Text("密码") },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                focusedLabelColor = Color.LightGray,
                unfocusedLabelColor = Color.Black,
                cursorColor = Color.LightGray,
            ),
            visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}


@Composable
@Preview
fun InputLearnPreview() {
    InputLearn()
}