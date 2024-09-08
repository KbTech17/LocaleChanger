package com.dp.localechanger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import java.util.Locale

object LocaleChanger {
    private const val SELECTED_LANGUAGE = "com.dp.localeHelper.language.2025"
    private const val SELECTED_COUNTRY = "com.dp.localeHelper.country.2025"
    private var initialized = false

    fun onAttach(context: Context): Context {
        if (!initialized) {
            Locale.setDefault(load(context))
            initialized = true
        }
        return updateContextResources(context, Locale.getDefault())
    }

    fun getLocale(context: Context): Locale = load(context)

    fun setLocale(context: Context, locale: Locale): Context {
        persist(context, locale)
        Locale.setDefault(locale)
        return updateContextResources(context, locale)
    }

    fun isRTL(locale: Locale): Boolean = Locales.RTL.contains(locale.language)

    private fun getPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(LocaleChanger::class.java.name, Context.MODE_PRIVATE)

    private fun persist(context: Context, locale: Locale?) {
        if (locale == null) return
        getPreferences(context).edit()
            .putString(SELECTED_LANGUAGE, locale.language)
            .putString(SELECTED_COUNTRY, locale.country)
            .apply()
    }

    private fun load(context: Context): Locale {
        val preferences = getPreferences(context)
        val default = Locale.getDefault()
        val language = preferences.getString(SELECTED_LANGUAGE, default.language) ?: return default
        val country = preferences.getString(SELECTED_COUNTRY, default.country) ?: return default
        return Locale(language, country)
    }

    private fun updateContextResources(context: Context, locale: Locale): Context {
        if (context.currentLocale == locale && context is Application) {
            return context
        }

        val resources = context.resources
        val configuration = resources.configuration
        configuration.setCurrentLocale(locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(configuration)
        } else {
            resources.updateConfiguration(configuration, resources.displayMetrics)
            context
        }
    }
}
