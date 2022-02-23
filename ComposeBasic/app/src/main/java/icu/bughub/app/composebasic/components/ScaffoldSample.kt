package icu.bughub.app.composebasic.components


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt


@Composable
fun ScaffoldSample() {

    val scaffoldState = rememberScaffoldState()
    // Consider negative values to mean 'cut corner' and positive values to mean 'round corner'
    val sharpEdgePercent = -50f
    val roundEdgePercent = 45f
// Start with sharp edges
    val animatedProgress = remember { Animatable(sharpEdgePercent) }
// Create a coroutineScope for the animation
    val coroutineScope = rememberCoroutineScope()
// animation value to animate shape
    val progress = animatedProgress.value.roundToInt()

// When progress is 0, there is no modification to the edges so we are just drawing a rectangle.
// This allows for a smooth transition between cut corners and round corners.
    val fabShape = when {
        progress < 0 -> {
            CutCornerShape(abs(progress))
        }
        progress == roundEdgePercent.toInt() -> {
            CircleShape
        }
        else -> {
            RoundedCornerShape(progress)
        }
    }
// lambda to call to trigger shape animation
    val changeShape: () -> Unit = {
        val target = animatedProgress.targetValue
        val nextTarget = if (target == roundEdgePercent) sharpEdgePercent else roundEdgePercent
        coroutineScope.launch {
            animatedProgress.animateTo(
                targetValue = nextTarget,
                animationSpec = TweenSpec(durationMillis = 600)
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Title")
            }, navigationIcon = {
                Icon(
                    imageVector = Icons.Default.NavigateBefore,
                    contentDescription = null
                )
            }, actions = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Edit")
            })
        },
        bottomBar = {
            BottomAppBar(cutoutShape = fabShape) {

            }
        },
        scaffoldState = scaffoldState,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text("Change Shape")
                },
                onClick = changeShape,
                shape = fabShape
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) {
        Text(text = "Scaffold Body Content")
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScaffoldSample1() {

    val navs = listOf("Home", "Study", "Profile")
    var currentNavIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Title")
        }, navigationIcon = {
            Icon(
                imageVector = Icons.Default.NavigateBefore,
                contentDescription = null
            )
        }, actions = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Edit")
        })
    }, bottomBar = {
        BottomNavigation() {
            navs.forEachIndexed { index, s ->
                BottomNavigationItem(
                    selected = currentNavIndex == index,
                    onClick = {
                        currentNavIndex = index
                    },
                    icon = {
                        BadgeBox(badgeContent = {
                            Text(text = "99+")
                        }) {
                            Icon(
                                Icons.Default.AccountBox,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text(s)
                    }
                )
            }
        }
    }
    ) {
        Text(text = "Scaffold Body Content")
    }
}

@Composable
fun ScaffoldSample2() {

    val navs = listOf("Home", "Study", "Profile")
    var currentNavIndex by remember {
        mutableStateOf(0)
    }

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(title = {
            Text(text = "Title")
        }, navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
            }
        }, actions = {
            IconButton(onClick = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        "已经添加成功了", actionLabel = "Done"
                    )
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Edit")
        })
    }, bottomBar = {
        BottomNavigation() {
            navs.forEachIndexed { index, s ->
                BottomNavigationItem(
                    selected = currentNavIndex == index,
                    onClick = {
                        currentNavIndex = index
                    },
                    icon = {
                        Icon(
                            Icons.Default.AccountBox,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(s)
                    }
                )
            }
        }
    },
        drawerContent = {
            Text(text = "Drawer Content")
        },
        drawerBackgroundColor = Color.Yellow,
        drawerContentColor = Color.Red,
        drawerScrimColor = Color.Green
    ) {
        Text(text = "Scaffold Body Content")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScaffoldSample3() {
    val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
    val coroutineScope = rememberCoroutineScope()

    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = {
            TopAppBar(title = {
                Text(text = "Title")
            }, navigationIcon = {
                IconButton(onClick = {
                    if (scaffoldState.isConcealed) {
                        coroutineScope.launch {
                            scaffoldState.reveal()
                        }
                    } else {
                        coroutineScope.launch {
                            scaffoldState.conceal()
                        }
                    }
                }) {
                    if (scaffoldState.isConcealed) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                }
            }, actions = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "已经添加成功了", actionLabel = "Done"
                        )
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Edit")
            })
        },
        frontLayerContent = {
            Text(text = "frontLayerContent")
        },
        backLayerContent = {
            LazyColumn() {
                items(50) {
                    ListItem(text = {
                        Text(text = "List item $it")
                    })
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScaffoldSample4() {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {
                Text(text = "Title")
            }, navigationIcon = {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null
                    )
                }
            }, actions = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            "已经添加成功了", actionLabel = "Done"
                        )
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Edit")
            })
        },
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(128.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Swipe up to expand sheet")
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(64.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Sheet Content")
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    coroutineScope.launch {
                        scaffoldState.bottomSheetState.collapse()
                    }
                }) {
                    Text(text = "close")
                }
            }
        },
        sheetPeekHeight = 128.dp
    ) {
        Text(text = "Body Content")
    }
}

@Preview
@Composable
fun ScaffoldSamplePreview() {
    ScaffoldSample4()
}

