package icu.bughub.app.app.compositionLocal

import androidx.compose.runtime.compositionLocalOf
import icu.bughub.app.app.viewmodel.UserViewModel

val LocalUserViewModel =
    compositionLocalOf<UserViewModel> { error("User View Model Context Not Found") }