package com.demo.compose

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val keyBoard = LocalSoftwareKeyboardController.current
    val focusUsername = remember { FocusRequester() }
    val focusPassword = remember { FocusRequester() }
    var inputUsername by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    val isInValidUsername by remember {
        derivedStateOf {
            if(inputUsername.isNotEmpty()) Patterns.EMAIL_ADDRESS.matcher(inputUsername).matches().not() else false
        }
    }
    val isInValidPassword by remember {
        derivedStateOf {
            if (inputPassword.isNotEmpty()) isInValidPassword(inputPassword) else false
        }
    }
    val isUsername by remember {
        derivedStateOf { inputUsername.isNotEmpty() }
    }
    val isPassword by remember {
        derivedStateOf { inputPassword.isNotEmpty() }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {

        Text(
            text = stringResource(id = R.string.txt_screen_title),
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
        )

        OutlinedTextField(
            value = inputUsername,
            onValueChange = { inputUsername = it },
            leadingIcon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            },
            trailingIcon = {
                //Icon(imageVector = Icons.Default.Info, contentDescription = null)
                if(isUsername) {
                    IconButton(
                        onClick = {
                            inputUsername = ""
                        }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusPassword.requestFocus()
                }
            ),
            // Text field label or hint when not focused.
            label = { Text(text = stringResource(id = R.string.txt_username_label)) },
            // Text field hint text when focused.
            placeholder = { Text(text = stringResource(id = R.string.txt_password_hint)) },
            // Text field error text.
            supportingText = {
                if (isInValidUsername) {
                    Text(text = stringResource(id = R.string.txt_username_error))
                }
            },
            isError = isInValidUsername,
            modifier = Modifier
                .focusRequester(focusUsername)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )

        OutlinedTextField(
            value = inputPassword,
            onValueChange = { inputPassword = it },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null)
            },
            trailingIcon = {
                //Icon(imageVector = Icons.Default.Info, contentDescription = null)
                if(isPassword) {
                    IconButton(
                        onClick = {
                            inputPassword = ""
                        }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyBoard?.hide()
                }
            ),
            label = { Text(text = stringResource(id = R.string.txt_password_label)) },
            placeholder = { Text(text = stringResource(id = R.string.txt_password_hint)) },
            supportingText = {
                if (isInValidPassword) {
                    Text(text = stringResource(id = R.string.txt_password_error))
                }
            },
            isError = isInValidPassword,
            modifier = Modifier
                .focusRequester(focusPassword)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        )

        Button(
            onClick = {
                if (inputUsername.isNotEmpty() && inputPassword.isNotEmpty()) {
                    keyBoard?.hide()
                    showToast(context = context, inputUsername)
                }
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            content = {
                Text(text = stringResource(id = R.string.txt_sign_in))
            }
        )
    }
}

// Function to show the toast message.
private fun showToast(context : Context, message:String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

private fun isInValidPassword(password : String) : Boolean {
    val digitRegex = Regex(".*\\d.*")
    val specialCharRegex = Regex(".*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")
    return password.length < 8 || !password.contains(digitRegex) || !password.contains(specialCharRegex)
}