package id.aflah.universitieslist.ui.components

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavHostController

@Composable
fun LinkText(webUrl: String, navController: NavHostController) {
    Text(
        text = webUrl,
        style = TextStyle(
            color = Color.Blue,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.clickable {
            val encodedUrl = Uri.encode(webUrl)
            navController.navigate("webview/$encodedUrl")
        }
    )
}