package com.led.game.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.toast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
fun Context.toast(@StringRes stringId: Int){
    toast(getString(stringId))
}