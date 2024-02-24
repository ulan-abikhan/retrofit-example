package kz.udev.restapp.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(

) {

    val coroutineScope = rememberCoroutineScope()

    val pages = listOf(
        OneTab("Liked", Icons.Default.FavoriteBorder),
        OneTab("Basket", Icons.Default.ShoppingCart),
        OneTab("Settings", Icons.Default.Settings)
    )

    val pagerState = rememberPagerState(initialPage = 2) {
        pages.size
    }

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        contentColor = Color.Black,
        indicator = {
            TabRowDefaults.SecondaryIndicator(
                Modifier.tabIndicatorOffset(it[pagerState.currentPage]),
                color = Color.Black,
                height = 1.dp
            )
        },
    ) {

        pages.forEachIndexed { index, item ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Black
            ) {
                Icon(imageVector = item.imageVector, contentDescription = null)

                Text(text = item.text)
            }
        }
    }

    HorizontalPager(state = pagerState) {
        Text(text = pages[it].text)
    }

}

data class OneTab(
    val text: String,
    val imageVector: ImageVector
)