package com.shahtott.alexon_task.data.local

//The class holds the user data in our app.

data class User(
    val name: String? = "",
    val email: String? = "",
    val phone: String? = "",
    val token: String? = "",
    val title: String? = "",
    val imageUrl: String? = "",
)
