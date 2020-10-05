package com.admin.chalengesicredi.module

import com.admin.chalengesicredi.ui.main.ActivityDetailEvent
import com.admin.chalengesicredi.ui.main.CheckinActivity
import com.admin.chalengesicredi.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * O módulo que fornece o serviço de injeção do Android para Activities.
 * */

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun detailevent(): ActivityDetailEvent

    @ContributesAndroidInjector
    abstract fun checkinact(): CheckinActivity




}
