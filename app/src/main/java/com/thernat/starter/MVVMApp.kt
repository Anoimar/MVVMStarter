package com.thernat.starter

import android.app.Activity
import android.app.Application
import com.thernat.starter.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class MVVMApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
      AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}
