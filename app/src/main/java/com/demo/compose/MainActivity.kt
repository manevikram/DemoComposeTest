package com.demo.compose

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.demo.compose.ui.theme.DemoComposeTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CustomTextField()
                    //CustomSeekBar()
                    //CustomListItem()
                    //CustomImageLoader()
                }
            }
        }
    }
}

@Composable
fun CustomSeekBar(){
    val context = LocalContext.current

    var sliderPosition by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Discrete SeekBar with tick marks
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
            },
            valueRange = 0f..100f,
            steps = 10,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(16.dp),
            colors = SliderDefaults.colors(
                activeTickColor = Color.Green, // Color of active tick marks
                inactiveTickColor = Color.Red, // Color of inactive tick marks
                activeTrackColor = Color.Green, // Color of active track
                inactiveTrackColor = Color.Red, // Color of inactive track
                thumbColor = Color.Black // Color of the thumb
            )
        )
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField() {
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoComposeTestTheme {
        CustomTextField()
        //CustomSeekBar()
        //CustomListItem()
        //CustomImageLoader()
    }
}


@Composable
fun CustomListItem() {
    val personList = listOf(
        "Vikram",
        "Sachin",
        "Satish",
        "Abe",
        "Erik",
        "Andres",
        "Vijay",
        "Sreedhar",
        "Kumud",
        "Siva",
        "Andres",
        "Vijay",
        "Sreedhar",
        "Kumud",
        "Siva"
    )
    val context = LocalContext.current
    LazyColumn {
        /*items(
            items = personList,
            itemContent = {person ->
                Text(text = person,
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            Toast
                                .makeText(context, person, Toast.LENGTH_SHORT)
                                .show()
                        })
                Divider()
            }
        )*/
        itemsIndexed(personList){
            index, item ->
            Text(text = "${index+1} -$item",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        Toast
                            .makeText(context, "$index - $item", Toast.LENGTH_SHORT)
                            .show()
                    })
            Divider()
        }
    }
}

@Composable
fun CustomImageLoader() {
    val imageUrl = "https://fastly.picsum.photos/id/71/600/600.jpg?hmac=TsAhMiQpHiJ9oAPvih0_bc4kWnV1Ev_iSw-fpGt9TGY"
    /*AsyncImage(
        model = imageUrl,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(800f/600f)
    )*/

    /*AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = stringResource(R.string.app_name),
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape)
    )*/

    SubcomposeAsyncImage(
        model = imageUrl,
        loading = {
            CircularProgressIndicator()
        },
        contentDescription = stringResource(R.string.app_name)
    )
}
