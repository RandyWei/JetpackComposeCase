package icu.bughub.app.app.ui.components


import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import icu.bughub.app.app.ui.navigation.Destinations
import icu.bughub.app.app.ui.screens.ArticleDetailScreen
import icu.bughub.app.app.ui.screens.MainFrame
import icu.bughub.app.app.ui.screens.VideoDetailScreen

/**
 * 导航控制器
 *
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {

    val navController = rememberAnimatedNavController()
    ProvideWindowInsets {
        AnimatedNavHost(
            navController = navController,
            startDestination = Destinations.HomeFrame.route
        ) {

            //首页大框架
            composable(
                Destinations.HomeFrame.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Left
                    )
                },
            ) {
                MainFrame(onNavigateToArticle = {
                    Log.i("===", "onNavigateToArticle")
                    navController.navigate(Destinations.ArticleDetail.route)
                }, onNavigateToVideo = {
                    navController.navigate(Destinations.VideoDetail.route)
                })
            }

            //文章详情页
            composable(
                Destinations.ArticleDetail.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right)
                },
            ) {
                ArticleDetailScreen(onBack = {
                    navController.popBackStack()
                })
            }

            //视频详情页
            composable(
                Destinations.VideoDetail.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentScope.SlideDirection.Left)
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentScope.SlideDirection.Right)
                },
            ) {
                VideoDetailScreen(onBack = {
                    navController.popBackStack()
                })
            }

        }
    }
}

@Preview
@Composable
fun NavHostAppPreview() {
    NavHostApp()
}

