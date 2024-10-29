package com.demo.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * Created by Vikram Mane on 10/27/24.
 */

@Composable
fun CenterSnapLazyRow(
    items: List<String>, // Replace with your data type
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = items.size-1
    )
    val coroutineScope = rememberCoroutineScope()

    // Calculate item size and screen center
    val itemWidth = 50.dp // Adjust based on your needs
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val contentPadding = (screenWidth - itemWidth) / 2

    LazyRow(
        state = lazyListState,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        contentPadding = PaddingValues(horizontal = contentPadding),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        flingBehavior = rememberSnapFlingBehavior(lazyListState)
    ) {
        items(items) { item ->
            Card(
                modifier = Modifier
                    .width(itemWidth)
                    .height(40.dp)
                    .clickable {
                        coroutineScope.launch {
                            // Smooth scroll to clicked item
                            lazyListState.animateScrollToItem(items.indexOf(item))
                        }
                    }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        //lazyListState.scrollToItem(items.size-1)
    }
}

// Example usage
@Composable
fun ExampleScreen() {
    val items = List(10) { "Item ${it + 1}" }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Snap Scrolling Demo",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        CenterSnapLazyRow(items = items)
    }
}