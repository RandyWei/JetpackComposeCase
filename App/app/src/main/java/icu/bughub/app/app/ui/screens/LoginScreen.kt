package icu.bughub.app.app.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import icu.bughub.app.app.R
import icu.bughub.app.app.compositionLocal.LocalUserViewModel

@Composable
fun LoginScreen(onClose: () -> Unit) {

//    //屏幕宽度
//    var screenWidth: Float
//    //屏幕高度
//    var screenHeight: Float
//    with(LocalDensity.current) {
//        screenWidth = LocalConfiguration.current.screenWidthDp.dp.toPx()
//        screenHeight = LocalConfiguration.current.screenHeightDp.dp.toPx()
//    }

    val userViewModel = LocalUserViewModel.current

    var userName by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {


        //背景图层
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        //右上往左下渐变层
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xffbb8378), Color.Transparent),
                        start = Offset(x = constraints.maxWidth.toFloat(), y = 0f),
                        end = Offset(x = 0f, y = constraints.maxHeight.toFloat())
                    )
                )
        )
        //左下往右上渐变层
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFF149EE7), Color.Transparent),
                        start = Offset(x = 0f, y = constraints.maxHeight.toFloat()),
                        end = Offset(x = constraints.maxWidth.toFloat(), y = 0f)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,

            ) {
            Column {
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    text = "用户登录",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier,
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = userName,
                    onValueChange = { userName = it },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(
                            text = "用户名",
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.White
                    )
                )

                TextField(
                    value = password,
                    onValueChange = { password = it },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Password,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = if (showPassword) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                showPassword = !showPassword
                            },
                            tint = Color.White
                        )
                    },
                    label = {
                        Text(
                            text = "密码",
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.LightGray,
                        focusedLabelColor = Color.LightGray,
                        unfocusedLabelColor = Color.LightGray,
                        cursorColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextButton(onClick = {
                    userViewModel.login(onClose = onClose)
                }) {
                    Text(text = "登录", color = Color.White)
                }
            }

            TextButton(onClick = { }) {
                Text(text = "还没有账号？点击立即注册", color = Color.LightGray, fontSize = 12.sp)
            }
        }

    }

}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen {}
}

