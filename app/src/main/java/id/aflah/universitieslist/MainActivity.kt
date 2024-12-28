package id.aflah.universitieslist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import id.aflah.universitieslist.ui.components.AppNavHost
import id.aflah.universitieslist.ui.theme.UniversitiesListTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UniversitiesListTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}