package icu.bughub.app.app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import icu.bughub.app.app.model.service.UserInfoManager
import icu.bughub.app.app.model.entity.UserInfoEntity
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class UserViewModel(context: Context) : ViewModel() {

    private val userInfoManager = UserInfoManager(context)


    var userInfo: UserInfoEntity? = null
        private set

    init {
        //其实这里可以使用 DataStore 的对象存储，直接存储整个对象。
        viewModelScope.launch {
            val userName = userInfoManager.userName.firstOrNull()
            userInfo = if (userName?.isNotEmpty() == true) {
                UserInfoEntity(userName)
            } else {
                null
            }
        }
    }

    //是否已登录
    val logged: Boolean
        get() {
            return userInfo != null
        }

    /**
     * 登录操作
     *
     */
    fun login(onClose: () -> Unit) {
        //模拟网络请求数据回传
        userInfo = UserInfoEntity("user001")
        viewModelScope.launch {
            userInfoManager.save("user001")
        }
        onClose()
    }
}