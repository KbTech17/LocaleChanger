package com.dp.localechanger

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.LocaleChangerAppCompatDelegate
import java.util.Locale

interface LocaleChangerDelegate {
    fun setLocale(activity: Activity, newLocale: Locale)
    fun attachBaseContext(newBase: Context): Context
    fun onPaused()
    fun onResumed(activity: Activity)
    fun onCreate(activity: Activity)
    fun getApplicationContext(applicationContext: Context): Context
    fun getAppCompatDelegate(delegate: AppCompatDelegate): AppCompatDelegate
}

class LocaleChangerDelegateImpl : LocaleChangerDelegate {
    private var locale: Locale = Locale.getDefault()
    private var appCompatDelegate: AppCompatDelegate? = null

    override fun getAppCompatDelegate(delegate: AppCompatDelegate) =
        appCompatDelegate ?: LocaleChangerAppCompatDelegate(delegate).apply {
            appCompatDelegate = this
        }

    override fun onCreate(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activity.window.decorView.layoutDirection =
                if (LocaleChanger.isRTL(Locale.getDefault())) View.LAYOUT_DIRECTION_RTL
                else View.LAYOUT_DIRECTION_LTR
        }
    }

    override fun setLocale(activity: Activity, newLocale: Locale) {
        LocaleChanger.setLocale(activity, newLocale)
        locale = newLocale
        activity.recreate()
    }

    override fun attachBaseContext(newBase: Context): Context = LocaleChanger.onAttach(newBase)

    override fun getApplicationContext(applicationContext: Context): Context = applicationContext

    override fun onPaused() {
        locale = Locale.getDefault()
    }

    override fun onResumed(activity: Activity) {
        if (locale == Locale.getDefault()) return
        activity.recreate()
    }
}

class LocaleApplicationDelegate {
    fun attachBaseContext(base: Context): Context = LocaleChanger.onAttach(base)

    fun onConfigurationChanged(context: Context) {
        LocaleChanger.onAttach(context)
    }

    fun getApplicationContext(context: Context): Context = LocaleChanger.onAttach(context)
}
