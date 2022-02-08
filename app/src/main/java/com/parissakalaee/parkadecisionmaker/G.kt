package com.parissakalaee.parkadecisionmaker

import android.app.Activity
import android.app.Application
import android.content.Context

class G : Application() {
    override fun onCreate() {
        // TODO Auto-generated method stub
        super.onCreate()
        context = applicationContext
    }

    companion object {
        var context: Context? = null
        const val LOG_TAG = "Decision"
        var currentActivity: Activity? = null
    }
}