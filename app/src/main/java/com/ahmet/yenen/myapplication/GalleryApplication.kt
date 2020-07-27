package com.ahmet.yenen.myapplication

import com.ahmet.yenen.myapplication.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GalleryApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return appComponent
    }

    private val appComponent = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

}