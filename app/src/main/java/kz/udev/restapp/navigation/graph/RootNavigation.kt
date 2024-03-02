package kz.udev.restapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kz.udev.restapp.navigation.bottom_nav.BottomNav
import kz.udev.restapp.navigation.util.Screen
import kz.udev.restapp.ui.screens.login.LoginScreen

@Composable
fun RootNavigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
    ) {

        composable(Screen.Login.route) {
            LoginScreen(onNext = {
                navController.navigate("home")
            })
        }


        composable(route = "home") {
            BottomNav(navController = rememberNavController())
        }

    }

}