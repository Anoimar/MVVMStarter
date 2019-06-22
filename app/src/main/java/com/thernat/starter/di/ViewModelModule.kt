package com.thernat.starter.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thernat.starter.ui.master.MasterViewModel
import com.thernat.starter.viewmodel.MVVMViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by m.rafalski on 07/06/2019.
 */
@Suppress("unused")
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MasterViewModel::class)
    abstract fun bindMasterViewModel(repoViewModel: MasterViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MVVMViewModelFactory): ViewModelProvider.Factory
}