package com.demo.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent

/**
 * Created by Vikram Mane on 10/3/24.
 */

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomDrawer(){

    val drawerState = remember {
        mutableStateOf(DrawerValue.CLOSED)
    }

    fun updateDrawerState(){
        if (drawerState.value == DrawerValue.CLOSED){
            drawerState.value = DrawerValue.OPEN
        } else {
            drawerState.value = DrawerValue.CLOSED
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Top App Bar")
            },
                actions = {
                    TextButton(onClick = {
                        updateDrawerState()
                    }) {
                        Text(text = "Side Panel")
                    }
                })
        },

    ) {
        BoxWithConstraints(modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
            contentAlignment = Alignment.TopEnd) {
            if (drawerState.value == DrawerValue.OPEN) {
                Box(
                    modifier = Modifier
                        .size(width = maxWidth / 2, height = maxHeight)
                        .background(Color.Blue)
                )
            }
        }
    }


}


enum class DrawerValue{
    OPEN,
    CLOSED
}