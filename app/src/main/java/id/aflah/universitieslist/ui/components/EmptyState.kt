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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.aflah.universitieslist.R
import id.aflah.universitieslist.ui.theme.Typography
import id.aflah.universitieslist.utils.Constants

@Composable
fun EmptyState(
    query: String,
) {
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
                contentDescription = stringResource(R.string.cd_empty_state_image),
            )
            Text(
                stringResource(R.string.title_empty_state),
                fontWeight = FontWeight.Bold,
                style = Typography.headlineSmall
            )
            Text(
                if (query.length >= Constants.MINIMUM_CHAR_TO_SEARCH) stringResource(
                    R.string.message_search_no_found,
                    query
                ) else stringResource(R.string.message_input_min_three_chars),
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