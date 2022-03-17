package icu.bughub.app.app.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import icu.bughub.app.app.model.entity.Category
import icu.bughub.app.app.model.entity.DataType
import icu.bughub.app.app.model.entity.SwiperEntity

class MainViewModel : ViewModel() {

    //分类数据
    val categories by mutableStateOf(
        listOf(
            Category("思想政治"),
            Category("法律法规"),
            Category("职业道德"),
            Category("诚信自律")
        )
    )

    //当前分类下标
    var categoryIndex by mutableStateOf(0)
        private set

    /**
     * 更新分类下标
     *
     * @param index
     */
    fun updateCategoryIndex(index: Int) {
        categoryIndex = index
    }


    //类型数据
    val types by mutableStateOf(
        listOf(
            DataType("相关资讯", Icons.Default.Description),
            DataType("视频课程", Icons.Default.SmartDisplay)
        )
    )

    //当前类型下标
    var currentTypeIndex by mutableStateOf(0)
        private set

    //是否文章列表
    var showArticleList by mutableStateOf(true)
        private set

    /**
     * 更新类型下标
     *
     * @param index
     */
    fun updateTypeIndex(index: Int) {
        currentTypeIndex = index
        showArticleList = currentTypeIndex == 0
    }

    //轮播图数据
    val swiperData = listOf(
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner1.webp"),
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner2.webp"),
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner3.webp"),
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner4.jpg"),
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner5.jpg")
    )

    //通知数据
    val notifications =
        listOf("人社部向疫情防控期", "湖北黄冈新冠肺炎患者治愈病例破千连续5治愈病例破千连续5", "安徽单日新增确诊病例首次降至个位数累计")


}