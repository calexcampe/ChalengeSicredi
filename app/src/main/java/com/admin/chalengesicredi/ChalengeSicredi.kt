package com.admin.chalengesicredi
import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import javax.inject.Named


class ChalengeSicredi : Application(), HasActivityInjector {

    @Inject
    @Named("activityDispatchingInjector")
    lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        initializeComponent()
        setInstance(this)
    }

    private fun initializeComponent() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingInjector
    }

    companion object {

        var appContext: Application? = null
            set


        @Synchronized
        fun setInstance(app: Application) {
            appContext = app
        }
    }
}
