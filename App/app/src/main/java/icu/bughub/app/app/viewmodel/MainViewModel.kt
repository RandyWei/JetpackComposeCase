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
import icu.bughub.app.app.model.service.HomeService

class MainViewModel : ViewModel() {

    private val homeService = HomeService.instance()

    //分类数据是否加载成功
    var categoryLoaded by mutableStateOf(false)
        private set

    //分类数据
    var categories by mutableStateOf(
        listOf(
            Category("思想政治1", "1"),
            Category("法律法规2", "2"),
            Category("职业道德3", "3"),
            Category("诚信自律4", "4")
        )
    )
        private set

    suspend fun categoryData() {
        val categoryRes = homeService.category()
        if (categoryRes.code == 0) {
            categories = categoryRes.data
            categoryLoaded = true
        } else {
            //不成功的情况下，读取 message
            val message = categoryRes.message
        }
    }

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
    var swiperData by mutableStateOf(
        listOf(
            SwiperEntity("https://docs.bughub.icu/compose/assets/banner5.jpg")
        )
    )
        private set

    var swiperLoaded by mutableStateOf(false)
        private set

    suspend fun swiperData() {
        val swiperRes = homeService.banner()
        if (swiperRes.code == 0 && swiperRes.data != null) {
            swiperData = swiperRes.data
            swiperLoaded = true
        } else {
            val message = swiperRes.message
        }
    }

    //通知数据
    val notifications =
        listOf("人社部向疫情防控期", "湖北黄冈新冠肺炎患者治愈病例破千连续5治愈病例破千连续5", "安徽单日新增确诊病例首次降至个位数累计")


}