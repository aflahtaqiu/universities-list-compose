package id.aflah.universitieslist.ui.components

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.aflah.universitieslist.ui.screens.MainScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController)
        }
        composable("webview/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")?.let {
                Uri.decode(it)
            }.orEmpty()
            WebViewComponent(url, navController)
        }
    }
}
