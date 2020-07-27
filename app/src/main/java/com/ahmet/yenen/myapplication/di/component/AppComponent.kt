package com.ahmet.yenen.myapplication.di.component

import android.app.Application
import com.ahmet.yenen.myapplication.di.module.*
import com.ahmet.yenen.myapplication.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidInjectionModule::class),
        (ActivityModule::class),
        (FragmentModule::class),
        (ViewModelModule::class),
        (AppModule::class),
        (NetworkModule::class)
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)
}