package id.aflah.universitieslist.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import id.aflah.universitieslist.domain.ResultState
import id.aflah.universitieslist.domain.entity.University
import id.aflah.universitieslist.ui.components.LinkText
import id.aflah.universitieslist.ui.components.SearchBar
import id.aflah.universitieslist.ui.theme.Typography
import id.aflah.universitieslist.utils.cornerRadius

@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val shouldAppBarVisible = remember { mutableStateOf(false) }
    var universitiesList = remember { mutableListOf<University>() }

    LaunchedEffect(Unit) {
        mainViewModel.getUniversitiesList("indonesia")
    }

    if (mainViewModel.universitiesListState.value is ResultState.Success<List<University>>) {
        universitiesList =
            (mainViewModel.universitiesListState.value as ResultState.Success<List<University>>).data as ArrayList
    }

    Scaffold(
        topBar = {
            if (shouldAppBarVisible.value) {
                SearchBar(shouldAppBarVisible, mainViewModel)
            } else {
                Text(
                    text = "Universities List",
                    maxLines = 1,
                    style = Typography.headlineLarge,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.cornerRadius(32),
                containerColor = Color.Yellow,
                onClick = {
                    shouldAppBarVisible.value = true
                },
            ) {
                Icon(Icons.Filled.Search, "", tint = Color.Black)
            }
        }
    ) {
        Box(Modifier.padding(it)) {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 9.dp, end = 9.dp)
            ) {
                items(universitiesList) { university ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .cornerRadius(8)
                            .padding(horizontal = 8.dp)
                    ) {
                        Column {
                            Text(university.name)
                            Text(university.country)
                            university.webPages.forEach { webPage ->
                                LinkText(webPage)
                            }
                        }
                    }
                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                }
            }
        }
        }
    }