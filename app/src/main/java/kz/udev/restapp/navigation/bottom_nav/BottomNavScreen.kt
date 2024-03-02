package kz.udev.restapp.navigation.bottom_nav

import androidx.annotation.DrawableRes
import kz.udev.restapp.R
import kz.udev.restapp.navigation.util.Screen

sealed class BottomNavScreen(
    val text: String,
    @DrawableRes val icon: Int,
    val route: String
) {

    data object Dashboard: BottomNavScreen(
        text = "Home",
        icon = R.drawable.ic_launcher_foreground,
        route = Screen.Dashboard.route
    )

    data object Library: BottomNavScreen(
        text = "Library",
        icon = R.drawable.ic_launcher_foreground,
        route = Screen.Library.route
    )

    data object Cart: BottomNavScreen(
        text = "Cart",
        icon = R.drawable.ic_launcher_foreground,
        route = Screen.Cart.route
    )

    data object Profile: BottomNavScreen(
        text = "Profile",
        icon = R.drawable.ic_launcher_foreground,
        route = Screen.Profile.route
    )

}