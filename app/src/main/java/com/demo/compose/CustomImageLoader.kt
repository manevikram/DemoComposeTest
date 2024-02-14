package com.demo.compose

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage

/**
 * Created by Vikram Mane on 2/10/24.
 */

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