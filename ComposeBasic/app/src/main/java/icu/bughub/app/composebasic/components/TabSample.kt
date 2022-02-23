package icu.bughub.app.composebasic.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TabSample() {

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    Column {

        TabRow(selectedTabIndex = selectedTabIndex) {
            Tab(
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Gray
            ) {
                Text(text = "Tab0")
            }

            Tab(
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = null
                    )
                },
                text = {
                    Text("Tab1")
                }
            )

            LeadingIconTab(
                selected = selectedTabIndex == 2,
                onClick = { selectedTabIndex = 2 },
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
                        contentDescription = null
                    )
                },
                text = {
                    Text("Tab2")
                }
            )

        }

        Text("current index : $selectedTabIndex")

    }

}

@Preview
@Composable
fun TabSamplePreview() {
    TabSample()
}

