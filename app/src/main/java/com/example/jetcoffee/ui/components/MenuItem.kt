package com.example.jetcoffee.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetcoffee.model.Menu
import com.example.jetcoffee.model.dummyMenu
import com.example.jetcoffee.ui.theme.JetCoffeeTheme

@Composable
fun MenuItem(menu: Menu, modifier: Modifier = Modifier) {
    Card(modifier = modifier.width(140.dp)) {
        Column {
            Image(
                painter = painterResource(id = menu.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = menu.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = menu.price,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MenuItemPreview() {
    JetCoffeeTheme {
        Surface {
            MenuItem(menu = dummyMenu[0], modifier = Modifier.padding(8.dp))
        }
    }
}
