package icu.bughub.app.app.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.statusBarsPadding


@Composable
fun ArticleDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "文章详情",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.NavigateBefore,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                onBack()
                            }
                            .padding(8.dp)
                    )
                },
                actions = {
                    Icon(imageVector = Icons.Default.TextFields, contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                //TODO 点击设置文字大小
                            }
                            .padding(8.dp))
                }
            )
        },
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .statusBarsPadding()
    ) {
        Text(
            text = "这是文章详情内容"
        )
    }
}

