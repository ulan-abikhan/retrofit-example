package kz.udev.restapp.navigation.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import kz.udev.restapp.navigation.util.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNav(
    navController: NavHostController
) {
    val screens = listOf(BottomNavScreen.Dashboard, BottomNavScreen.Cart, BottomNavScreen.Library, BottomNavScreen.Profile)

    Scaffold(
        bottomBar = {
            Surface(
                shadowElevation = 5.dp,
                color = Color.Transparent
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.White,
                        )
//                horizontalArrangement = Arrangement.SpaceAround
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    screens.forEach { screen ->
                        val selected =
                            currentDestination?.hierarchy?.any { it.route == screen.route } == true

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Bottom,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                                .padding(10.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null,
                                tint = if (selected) Color.Red
                                else Color.DarkGray
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = screen.text,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (selected) Color.Red
                                else Color.DarkGray

                            )

                        }
                    }

                }
            }
        }
    ) { _ ->
        NavHost(navController = navController, startDestination = Screen.Dashboard.route) {
            composable(route = Screen.Dashboard.route) {
//                FirstScreen(navigateToNext = {})
            }

            composable(route = Screen.Cart.route) {
//                SecondScreen(onBackPressed = { }) {
//
//                }
            }

            composable(route = Screen.Library.route) {
//                ThirdScreen(word = "") {
//
//                }
            }

            composable(route = Screen.Profile.route) {

            }
        }
    }

}