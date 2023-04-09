package com.example.mysunflowerstudy

import android.app.Application
import androidx.work.Configuration

class SunflowerApplication: Application(), Configuration.Provider {

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) android.util.Log.DEBUG else android.util.Log.ERROR)
            .build()
}