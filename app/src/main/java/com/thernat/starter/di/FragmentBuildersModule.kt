package com.thernat.starter.di

import com.thernat.starter.ui.master.MasterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMasterFragment(): MasterFragment

}
