package icu.bughub.app.app.ui.navigation

sealed class Destinations(val route: String) {
    //首页大框架
    object HomeFrame : Destinations("HomeFrame")

    //文章详情页
    object ArticleDetail : Destinations("ArticleDetail")
    //视频详情页
    object VideoDetail : Destinations("VideoDetail")
}
