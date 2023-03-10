package com.shahtott.alexon_task.util

import android.content.Context
import android.content.Intent
import com.shahtott.alexon_task.MainActivity
import com.shahtott.alexon_task.auth.AuthActivity

fun Context.toAuthActivity() {
    startActivity(Intent(this, AuthActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    })
}


fun Context.toMainActivity() {
    startActivity(Intent(this, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    })
}