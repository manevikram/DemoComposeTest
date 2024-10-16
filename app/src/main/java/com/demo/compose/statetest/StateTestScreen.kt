package com.demo.compose.statetest

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Vikram Mane on 10/12/24.
 */
@Composable
fun StateTestScree(viewModel: StateTestViewModel){

    // remember - Persist state with recomposition
    // rememberSavable - Persist state even with config change
    // View Model and LiveData - Hoist the state for reusability
    val name by viewModel.name.observeAsState(initial = "")
    val surname by viewModel.surname.observeAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        MyText("$name $surname")
        MyTextField(name, onNameChange = {
            viewModel.nameUpdate(it)
        })

        MyTextField(surname, onNameChange = {
            viewModel.surnameUpdate(it)
        })
    }
}

@Composable
fun MyText(name: String) {
    Text(
        text = "Hello - $name",
        style = TextStyle(fontSize = 30.sp)
    )
}

@Composable
fun MyTextField(name: String, onNameChange : (String)-> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = {
           onNameChange(it)
        },
        label = {
            Text(text = "Enter Name")
        })
}