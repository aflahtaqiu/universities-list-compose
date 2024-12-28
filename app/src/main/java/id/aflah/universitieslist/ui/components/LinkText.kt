package id.aflah.universitieslist.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun LinkText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            color = Color.Blue,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier.clickable {
            // TODO handle redirect to webview later
        }
    )
}