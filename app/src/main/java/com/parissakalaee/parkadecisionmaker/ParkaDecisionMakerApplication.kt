package com.parissakalaee.parkadecisionmaker

import android.app.Application
import timber.log.Timber

class ParkaDecisionMakerApplication : Application() {
    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}