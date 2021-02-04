package com.led.game.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.led.game.R
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

fun Context.toast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
fun Context.toast(@StringRes stringId: Int){
    toast(getString(stringId))
}

fun Context.drawableTint(@DrawableRes res: Int, @ColorRes color: Int): Drawable?{
    return getDrawableRes(res)?.let {
        it.mutate()
        val wrap = DrawableCompat.wrap(it)
        DrawableCompat.setTint(wrap, getColorRes(color))
        DrawableCompat.setTintMode(wrap, PorterDuff.Mode.SRC_IN)
        return@let wrap
    }
}

fun Context.getDrawableRes(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(this, id)
}

fun Int.pxToDp(): Float {
    return (this / Resources.getSystem().displayMetrics.scaledDensity)
}

fun Int.dpToPx(): Float {
    return (this * Resources.getSystem().displayMetrics.scaledDensity)
}

fun Context.getDrawableChangedRes(@DrawableRes id: Int, radiusDp: Int = -1, @ColorRes color: Int= -1): GradientDrawable? {
    val drawable =  this.getDrawableRes(id) as? GradientDrawable
    drawable?.mutate()
    if (radiusDp != -1){
        drawable?.cornerRadius = radiusDp.dpToPx()
    }
    if (color != -1){
        drawable?.setColor(this.getColorRes(color))
    }
    return drawable
}

fun Context.getDrawable(radiusDp: Int = 10, @ColorRes color: Int= R.color.white): GradientDrawable? {
    val drawable =  GradientDrawable()
    drawable.cornerRadius = radiusDp.dpToPx()
    drawable.setColor(this.getColorRes(color))
    return drawable
}

fun Context.getColorRes(@ColorRes res: Int): Int{
    return ContextCompat.getColor(this, res)
}

inline fun Long.delayRun(crossinline fun1: () -> Unit): Disposable {
    return Observable.timer(this, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .subscribe({
            fun1()
        },{
            Timber.e(it)
        })
}