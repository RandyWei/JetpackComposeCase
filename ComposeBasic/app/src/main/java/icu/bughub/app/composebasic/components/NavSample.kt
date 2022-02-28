package icu.bughub.app.composebasic.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun NavSample() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.First.route
    ) {

        composable(route = Screen.First.route) {
            FirstScreen() {
                navController.navigate("${Screen.Second.route}/韦爵爷/19")
            }
        }

        composable(
            route = "${Screen.Second.route}/{name}/{age}", arguments = listOf(
                navArgument("age") {
                    type = NavType.IntType
                }
            )
        ) {
            val name = it.arguments?.getString("name")
            val age = it.arguments?.getInt("age") ?: 18
            SecondScreen(name, age) {
                navController.navigate("Third?carName=五菱宏光 Mini")
            }
        }

        composable(
            route = "${Screen.Third.route}?carName={carName}", arguments = listOf(
                navArgument("carName") {
                    nullable = true
                    defaultValue = "卡宴"
                }
            )
        ) {
            val carName = it.arguments?.getString("carName")

            ThirdScreen(carName) {
                navController.popBackStack(
                    Screen.Second.route,
                    inclusive = true
                )
            }
        }

    }

}

sealed class Screen(val route: String) {
    object First : Screen("First")
    object Second : Screen("Second")
    object Third : Screen("Third")
}

@Composable
fun FirstScreen(onNavigateToSecond: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "First Screen")

        Button(onClick = {
            onNavigateToSecond()
        }) {
            Text(text = "Navigate to second screen")
        }
    }
}

@Composable
fun SecondScreen(name: String?, age: Int, onNavigateToThird: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Second Screen:$name  今年${age}岁")

        Button(onClick = {
            onNavigateToThird()
        }) {
            Text(text = "Navigate to third screen")
        }
    }
}

@Composable
fun ThirdScreen(carName: String?, onBackToRoot: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Third Screen:$carName")

        Button(onClick = {
            onBackToRoot()
        }) {
            Text(text = "Navigate back to root screen")
        }
    }
}

@Preview
@Composable
fun NavSamplePreview() {
    NavSample()
}

