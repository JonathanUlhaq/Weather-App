package com.belajar.aplikasicuaca.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.belajar.aplikasicuaca.navigation.Route
import kotlin.math.exp

@Composable
fun TopBar(
    city: String,
    country: String = "",
    isMainScreen: Boolean,
    value: MutableState<String> = mutableStateOf(""),
    tersimpan: MutableState<Boolean> = mutableStateOf(true),
    onSave: () -> Unit = {},
    navController: NavController,
    deleteText: () -> Unit = {},
    onAction: () -> Unit = {}
) {

    val search = remember {
        mutableStateOf(false)
    }

    val expand = remember {
        mutableStateOf(false)
    }

    if (expand.value) {
        DropDownMenu(
            expand = expand,
            navController = navController
        )
    }

    TopAppBar(
        title = {

            AnimatedVisibility(tersimpan.value == false) {
                IconButton(onClick = { onSave.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Fav",
                        tint = Color.Red
                    )
                }
            }
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                ) {
                    append("$city, ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    ) {
                        append("$country")
                    }
                }
            })
        },
        navigationIcon = {
            if (isMainScreen == false) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            AnimatedVisibility(visible = isMainScreen) {
                AnimatedVisibility(visible = search.value == false) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = {
                            search.value = true
                            deleteText.invoke()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        }

                        IconButton(onClick = { expand.value = true }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null
                            )
                        }
                    }
                }

                AnimatedVisibility(visible = search.value == true) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        InputKeyboard(value = value.value,
                            onValue = { value.value = it },
                            onAction = {
                                onAction.invoke()
                                search.value = false
                            }) {
                            search.value = false
                        }
                    }
                }

            }

        },
        backgroundColor = Color.White,
        elevation = 8.dp
    )
}

@Composable
fun DropDownMenu(
    expand: MutableState<Boolean>,
    navController: NavController
) {

    val listMenu = listOf(
        "Favorite",
        "About",
        "Setting"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .offset(x = -15.dp, y = 50.dp)
    ) {
        DropdownMenu(
            expanded = expand.value,
            onDismissRequest = { expand.value = false }) {
            listMenu.forEach {
                DropdownMenuItem(onClick = {
                    when (it) {
                        "Favorite" -> {
                            navController.navigate(Route.Favorite.route)
                        }
                        "Setting" -> {
                            navController.navigate(Route.Setting.route)
                        }
                        "About" -> {
                            navController.navigate(Route.About.route)
                        }
                    }
                }) {
                    Icon(
                        imageVector = when (it) {
                            "Favorite" -> {
                                Icons.Default.Favorite
                            }
                            "About" -> {
                                Icons.Default.Info
                            }
                            else -> {
                                Icons.Default.Settings
                            }
                        },
                        contentDescription = null
                    )
                    Text(
                        text = it,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}