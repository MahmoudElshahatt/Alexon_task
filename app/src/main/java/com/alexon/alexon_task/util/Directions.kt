package com.alexon.alexon_task.util

import android.content.Context
import android.content.Intent
import com.alexon.alexon_task.ui.main.MainActivity
import com.alexon.alexon_task.ui.auth.AuthActivity

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