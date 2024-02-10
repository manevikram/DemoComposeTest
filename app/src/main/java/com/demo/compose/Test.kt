package com.demo.compose

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class Test {

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
                            Toast.makeText(context, person, Toast.LENGTH_SHORT).show()
                        })
                    Divider()
                }
            )*/

            itemsIndexed(personList) { index, item ->
                Text(text = "${index + 1}, $item",
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
}