package icu.bughub.app.composebasic.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import icu.bughub.app.composebasic.R


@Composable
fun TextSample() {
//    Text(text = "我终于要学会Jetpack Compose 了！")

    val annotatedString = buildAnnotatedString {
        append("点击登录即代表您已知悉和同意")
        //往字符串中添加注解，tag 为标识，直到遇到 pop()
        pushStringAnnotation("protocol", "https://docs.bughub.icu/compose")
        withStyle(style = SpanStyle(Color.Blue, textDecoration = TextDecoration.Underline)) {
            append("用户协议")
        }
        pop()

        append("和")

        pushStringAnnotation("privacy", "https://github.com/RandyWei")
        withStyle(style = SpanStyle(Color.Blue, textDecoration = TextDecoration.Underline)) {
            append("隐私政策")
        }
        pop()
    }

    ClickableText(text = annotatedString, onClick = { offset ->
        //从字符串中根据 tag 查找注解
        annotatedString.getStringAnnotations("protocol", start = offset, end = offset).firstOrNull()
            ?.let { annotation ->
                Log.d("====", "你点击到${annotation.item}")
            }

        annotatedString.getStringAnnotations("privacy", start = offset, end = offset).firstOrNull()
            ?.let { annotation ->
                Log.d("====", "你点击到${annotation.item}")
            }

    })

}


@Preview
@Composable
fun TextSamplePreview() {
    TextSample()
}