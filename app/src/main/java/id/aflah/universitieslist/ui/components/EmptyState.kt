package id.aflah.universitieslist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.aflah.universitieslist.R
import id.aflah.universitieslist.ui.theme.Typography

@Composable
fun EmptyState(query: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_empty_state),
                contentDescription = "Empty State Image",
            )
            Text(
                "Oppss..",
                fontWeight = FontWeight.Bold,
                style = Typography.headlineSmall
            )
            Text(
                "Seems there are no university with $query name",
                style = Typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    EmptyState("lorem ipsum lorem ipsum")
}