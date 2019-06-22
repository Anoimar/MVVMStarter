package com.thernat.starter.di

import android.app.Application
import com.thernat.starter.MVVMApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by m.rafalski on 07/06/2019.
 */
/**
 * Helper class to automatically inject fragments if they implement [Injectable].
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MVVMApp)
}
