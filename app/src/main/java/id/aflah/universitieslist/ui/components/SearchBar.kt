package id.aflah.universitieslist.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import id.aflah.universitieslist.ui.screens.MainViewModel

@Composable
fun SearchBar(shouldAppBarVisible: MutableState<Boolean>, viewModel: MainViewModel){
    var text by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()
    BackHandler(shouldAppBarVisible.value) {
        shouldAppBarVisible.value = false
    }
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = text,
            colors = TextFieldDefaults.colors(
                cursorColor = Color.Black,
                disabledLabelColor = Color.Blue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                text = it
                if (text.length >= 3) {
                    viewModel.searchUniversitiesByName(query = text)
                }
            },
            singleLine = true,
            trailingIcon = {
                if (text.trim().isNotEmpty()) {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .offset(x = 10.dp)
                            .clickable {
                                text = ""
                                viewModel.getUniversitiesList("indonesia")
                                viewModel.isSearchState.value = false
                            }
                    )
                } else {
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = "clear and back",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .offset(x = 10.dp)
                            .clickable {
                                text = ""
                                viewModel.getUniversitiesList("indonesia")
                                viewModel.isSearchState.value = false
                                shouldAppBarVisible.value = false
                            }
                    )
                }
            },
            placeholder = {
                Text("Search...")
            }
        )
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}