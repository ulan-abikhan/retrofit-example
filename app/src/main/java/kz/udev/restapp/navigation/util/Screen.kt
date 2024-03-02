package kz.udev.restapp.navigation.util

sealed class Screen(val route: String) {

    data object Login: Screen("login")

    data object Dashboard: Screen("dashboard")

    data object ProductDetail: Screen("product_detail")

    data object Library: Screen("library")

    data object Cart: Screen("cart")

    data object Profile: Screen("profile")

}