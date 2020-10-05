package com.admin.chalengesicredi.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import javax.inject.Named

abstract class BaseActivity<V : ViewModel >: AppCompatActivity(),
    HasSupportFragmentInjector {

    @Inject
    @Named("fragmentAndroidInjector")
    lateinit var fragmentAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var viewModel: V

    @get:LayoutRes
    abstract val layoutRes: Int

    @Inject
    @Named("viewModelFactory")
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView( layoutRes)
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModel())

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentAndroidInjector
    }
    abstract fun getViewModel(): Class<V>

}

