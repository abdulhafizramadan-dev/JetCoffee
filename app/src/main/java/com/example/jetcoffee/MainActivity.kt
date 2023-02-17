package com.example.jetcoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetcoffee.model.*
import com.example.jetcoffee.ui.components.HomeSection
import com.example.jetcoffee.ui.components.MenuItem
import com.example.jetcoffee.ui.components.SearchBar
import com.example.jetcoffee.ui.theme.JetCoffeeTheme
import com.example.jetcoffee.ui.theme.LightGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetCoffeeTheme {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(bottomBar = { BottomBar() }) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)
                .verticalScroll(rememberScrollState())
            ) {
                Banner()
                HomeSection(title = R.string.section_category) {
                    CategoryRow()
                }
                HomeSection(title = R.string.section_favorite_menu) {
                    MenuRow(listMenu = dummyMenu)
                }
                HomeSection(title = R.string.section_best_seller_menu) {
                    MenuRow(listMenu = dummyBestSellerMenu)
                }
            }
        }
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Composable
fun CategoryRow(modifier: Modifier = Modifier) {
    LazyRow(modifier = modifier, contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(items = dummyCategory, key = { it.textCategory }) {category ->
            CategoryItem(category = category)
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(items = listMenu, key = { it.title }) { menu ->
            MenuItem(menu = menu)
        }
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = R.string.menu_home,
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = R.string.menu_favorite,
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = R.string.menu_profile,
                icon = Icons.Default.AccountCircle
            ),
        )
        navigationItems.forEach { item ->
            BottomNavigationItem(
                selected = navigationItems.first() == item,
                onClick = { },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(id = item.title) ) },
                unselectedContentColor = LightGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetCoffeeAppPreview() {
    JetCoffeeTheme {
        JetCoffeeApp()
    }
}

@Preview
@Composable
fun CategoryRowPreview() {
    JetCoffeeTheme {
        Surface {
            CategoryRow()
        }
    }
}
