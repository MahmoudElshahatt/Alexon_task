package com.shahtott.alexon_task.util.snakebar

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar


fun showSnakeBar(parentLayout: View, message: String, length: Int = Snackbar.LENGTH_SHORT) {
    val snack = Snackbar.make(parentLayout, message, length)
    val view: View = snack.view
    val params = view.layoutParams as FrameLayout.LayoutParams
    params.gravity = Gravity.TOP
    view.layoutParams = params
    snack.show()
}