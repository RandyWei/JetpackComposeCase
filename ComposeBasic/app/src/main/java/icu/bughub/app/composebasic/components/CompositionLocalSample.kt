package icu.bughub.app.composebasic.components


import android.net.wifi.hotspot2.pps.HomeSp
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun CompositionLocalSample() {

    val navController = rememberNavController()
    val user = User("Test")

    CompositionLocalProvider(LocalActiveUser provides user) {
        NavHost(navController = navController, startDestination = "Home") {
            composable("Home") {
                HomeScreen {
                    navController.navigate("Detail")
                }
            }

            composable("Detail") {
                DetailScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(onTap: () -> Unit) {
    Column {
        Text(text = "HomeScreen:${LocalActiveUser.current.name}")

        Button(onClick = { onTap() }) {
            Text(text = "Navigate to Detail")
        }
    }
}

@Composable
fun DetailScreen() {
    Text(text = "DetailScreen:${LocalActiveUser.current.name}")
}

val LocalActiveUser = compositionLocalOf<User> { error("user is null") }

data class User(val name: String)

@Preview
@Composable
fun CompositionLocalSamplePreview() {
    CompositionLocalSample()
}

