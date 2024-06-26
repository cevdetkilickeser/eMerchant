package com.cevdetkilickeser.emerchant.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.emerchant.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(128.dp))

        Image(
            painter = painterResource(R.drawable.img_login),
            contentDescription = "",
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black,
                cursorColor = Color.Black,
                selectionColors = TextSelectionColors(Color.Black, Color.Gray)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black,
                cursorColor = Color.Black,
                selectionColors = TextSelectionColors(Color.Black, Color.Gray)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                onLoginClick(username.value, password.value)
                keyboardController?.hide()
            },
            enabled = username.value.isNotEmpty() && password.value.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (username.value.isNotEmpty() && password.value.isNotEmpty()) Color.Black else Color.Gray
            )
        ) {
            Text("Login")
        }
    }
}
