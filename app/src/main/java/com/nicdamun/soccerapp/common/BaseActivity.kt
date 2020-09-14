package com.nicdamun.soccerapp.common

import android.content.res.Configuration
import android.os.Bundle
import com.nicdamun.soccerapp.R
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(resources.configuration, shouldRecreate = false)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setTheme(newConfig)
    }

    private fun setTheme(config: Configuration, shouldRecreate: Boolean = true) {
        when(config.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> setTheme(R.style.LightMode)
            Configuration.UI_MODE_NIGHT_YES -> setTheme(R.style.DarkMode)
        }
        if (shouldRecreate) recreate()
    }
}