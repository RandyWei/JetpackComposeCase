package icu.bughub.app.app.model.entity

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 导航栏对象
 *
 * @property title  导航栏的标题
 * @property icon 导航栏图标
 */
data class NavigationItem(
    val title: String, //底部导航栏的标题
    val icon: ImageVector//底部导航栏图标
)
