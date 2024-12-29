package id.aflah.universitieslist.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import id.aflah.universitieslist.domain.entity.University
import id.aflah.universitieslist.ui.theme.Typography

@Composable
fun UniversityItem(university: University, navHostController: NavHostController) {
    Card(
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.padding(2.dp).fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(university.name, style = Typography.bodyLarge)
            Text(
                university.country,
                style = Typography.bodySmall,
                modifier = Modifier.padding(vertical = 6.dp)
            )
            university.webPages.forEach { webPage ->
                LinkText(webPage, navHostController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUniversityItem() {
    val fakeUniversity = University(
        name = "Universitas Indonesia",
        country = "Indonesia",
        webPages = listOf("http://www.ui.ac.id/")
    )
    UniversityItem(fakeUniversity, rememberNavController())
}