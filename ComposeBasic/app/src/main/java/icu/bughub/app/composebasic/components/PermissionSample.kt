package icu.bughub.app.composebasic.components


import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.*


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionSample() {
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA
    )

    Scaffold(topBar = {
        TopAppBar(title = { Text("Permission Demo") })
    }) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            when (permissionState.status) {
                PermissionStatus.Granted -> {
                    Text("已经同意了相机权限")
                }
                //权限拒绝
                is PermissionStatus.Denied -> {
                    Column {
                        val text = if (permissionState.status.shouldShowRationale) {
                            //已经点击获取权限，此时拒绝
                            "相机权限已拒绝，点击按钮再次请求"
                        } else {
                            //默认情况下的拒绝
                            "相机权限已被禁止"
                        }
                        Text(text = text)
                        Button(onClick = {
                            permissionState.launchPermissionRequest()
                        }) {
                            Text("点击获取权限")
                        }
                    }
                }

            }

        }
    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionSample1() {
    val permissionsState = rememberMultiplePermissionsState(
        permissions =
        listOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    )

    Scaffold(topBar = {
        TopAppBar(title = { Text("Permissions Demo") })
    }) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            permissionsState.permissions.forEach { permissionState ->

                when (permissionState.permission) {
                    Manifest.permission.CAMERA -> {
                        when (permissionState.status) {
                            PermissionStatus.Granted -> {
                                Text("已经同意了相机权限")
                            }
                            //权限拒绝
                            is PermissionStatus.Denied -> {
                                Column {
                                    val text = if (permissionState.status.shouldShowRationale) {
                                        //已经点击获取权限，此时拒绝
                                        "相机权限已拒绝，点击按钮再次请求"
                                    } else {
                                        //默认情况下的拒绝
                                        "相机权限已被禁止"
                                    }
                                    Text(text = text)
                                }
                            }

                        }
                    }

                    Manifest.permission.RECORD_AUDIO -> {
                        when (permissionState.status) {
                            PermissionStatus.Granted -> {
                                Text("已经同意了录音权限")
                            }
                            //权限拒绝
                            is PermissionStatus.Denied -> {
                                Column {
                                    val text = if (permissionState.status.shouldShowRationale) {
                                        //已经点击获取权限，此时拒绝
                                        "录音权限已拒绝，点击按钮再次请求"
                                    } else {
                                        //默认情况下的拒绝
                                        "录音权限已被禁止"
                                    }
                                    Text(text = text)
                                }
                            }

                        }
                    }
                }
            }

            Button(onClick = {
                permissionsState.launchMultiplePermissionRequest()
            }) {
                Text("点击获取权限")
            }

        }
    }

}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionSample2() {
    val permissionState =
        rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    val backgroundPermissionState =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            rememberPermissionState(permission = Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        } else {
            TODO("VERSION.SDK_INT < Q")
        }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Permissions Demo") })
    }) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            when (permissionState.status) {
                PermissionStatus.Granted -> {
                    Button(onClick = {
                        backgroundPermissionState.launchPermissionRequest()
                    }) {
                        Text("前台定位权限已同意，点击获取后台定位权限")
                    }
                }
                //权限拒绝
                is PermissionStatus.Denied -> {
                    Column {
                        val text = if (permissionState.status.shouldShowRationale) {
                            //已经点击获取权限，此时拒绝
                            "前台定位权限已拒绝，点击按钮再次请求"
                        } else {
                            //默认情况下的拒绝
                            "前台定位权限已被禁止"
                        }
                        Text(text = text)
                    }
                }
            }

            when(backgroundPermissionState.status){
                PermissionStatus.Granted ->{
                    Text(text = "后台定位权限已同意")
                }

                //权限拒绝
                is PermissionStatus.Denied -> {
                    Column {
                        val text = if (permissionState.status.shouldShowRationale) {
                            //已经点击获取权限，此时拒绝
                            "后台定位权限已拒绝，点击按钮再次请求"
                        } else {
                            //默认情况下的拒绝
                            "后台定位权限已被禁止"
                        }
                        Text(text = text)
                    }
                }
            }

            Button(onClick = {
                permissionState.launchPermissionRequest()
            }) {
                Text("点击获取权限")
            }

        }
    }

}

@Preview
@Composable
fun PermissionSamplePreview() {
    PermissionSample2()
}

