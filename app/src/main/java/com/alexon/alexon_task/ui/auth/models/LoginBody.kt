package com.alexon.alexon_task.ui.auth.models

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class LoginBody(
    @field:Json(name = "password")
    val password: String? = "", // 0lelplR
    @field:Json(name = "username")
    val username: String? = "" // kminchelle
)