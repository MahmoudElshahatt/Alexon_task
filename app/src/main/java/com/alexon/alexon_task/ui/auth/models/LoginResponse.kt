package com.alexon.alexon_task.ui.auth.models


import com.squareup.moshi.Json
import androidx.annotation.Keep

@Keep
data class LoginResponse(
    @field:Json(name = "email")
    val email: String? = "", // kminchelle@qq.com
    @field:Json(name = "firstName")
    val firstName: String? = "", // Jeanne
    @field:Json(name = "gender")
    val gender: String? = "", // female
    @field:Json(name = "id")
    val id: Int? = 0, // 15
    @field:Json(name = "image")
    val image: String? = "", // https://robohash.org/autquiaut.png
    @field:Json(name = "lastName")
    val lastName: String? = "", // Halvorson
    @field:Json(name = "token")
    val token: String? = "", // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTUsInVzZXJuYW1lIjoia21pbmNoZWxsZSIsImVtYWlsIjoia21pbmNoZWxsZUBxcS5jb20iLCJmaXJzdE5hbWUiOiJKZWFubmUiLCJsYXN0TmFtZSI6IkhhbHZvcnNvbiIsImdlbmRlciI6ImZlbWFsZSIsImltYWdlIjoiaHR0cHM6Ly9yb2JvaGFzaC5vcmcvYXV0cXVpYXV0LnBuZyIsImlhdCI6MTY3ODQ2MTQ4NiwiZXhwIjoxNjc4NDY1MDg2fQ.yX-M2eysPSsTfZ-HGcXlOx7fIsioB7wLi04B2P83MTo
    @field:Json(name = "username")
    val username: String? = "" // kminchelle
)