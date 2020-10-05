package com.admin.chalengesicredi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.admin.chalengesicredi.ui.main.viewmodel.DetailViewModel
import com.admin.chalengesicredi.ui.main.viewmodel.MainViewModel
import com.admin.chalengesicredi.module.ViewModelFactory
import com.admin.chalengesicredi.module.ViewModelKey
import com.admin.chalengesicredi.ui.main.viewmodel.CheckinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap




/**
 * Nos faz injetar dependências via injeção de construtor
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModelViewModel(mainkViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindsMainViewModelViewModel2(detailViewModel: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CheckinViewModel::class)
    abstract fun bindscheckinViewModel(checkinViewModel: CheckinViewModel): ViewModel


    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory



}
