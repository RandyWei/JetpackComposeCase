package icu.bughub.app.app.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import icu.bughub.app.app.model.entity.UserInfoEntity
import icu.bughub.app.app.model.service.UserInfoManager
import icu.bughub.app.app.model.service.UserService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import okhttp3.internal.and
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class UserViewModel(context: Context) : ViewModel() {

    private val userInfoManager = UserInfoManager(context)
    private val userService = UserService.instance()

    var userName by mutableStateOf("")

    var password by mutableStateOf("")

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

    //是否正在登录
    var loading by mutableStateOf(false)
        private set

    var error by mutableStateOf("")
        private set

    /**
     * 登录操作
     *
     */
    suspend fun login(onClose: () -> Unit) {
        error = ""
        loading = true
        val res = userService.signIn(userName, md5(password))
        if (res.code == 0 && res.data != null) {
            userInfo = res.data
            userInfoManager.save(userName)
            onClose()
        } else {
            //失败
            error = res.message
        }
        loading = false
    }

    fun md5(content: String): String {
        val hash = MessageDigest.getInstance("MD5").digest(content.toByteArray())
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            var str = Integer.toHexString(b.toInt())
            if (b < 0x10) {
                str = "0$str"
            }
            hex.append(str.substring(str.length - 2))
        }
        return hex.toString()
    }


    fun clear() {
        viewModelScope.launch {
            userInfoManager.clear() //清除本地数据存储
            userInfo = null //置空内存数据
        }
    }
}