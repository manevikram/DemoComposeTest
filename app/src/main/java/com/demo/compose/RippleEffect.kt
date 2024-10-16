package com.demo.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Vikram Mane on 2/10/24.
 */

@Composable
fun RippleEffect() {
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(
            text = "Hello World",
            modifier = Modifier
                .clickable(
                    /*interactionSource = remember {
                        MutableInteractionSource()
                    },*/
                   /* indication = rememberRipple(
                        color = Color.Red
                    ),*/
                    onClick = {}
                )
                .padding(16.dp),
            fontSize = 24.sp
        )
    }
}