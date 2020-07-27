package com.ahmet.yenen.myapplication.di.module

import android.provider.ContactsContract
import com.ahmet.yenen.myapplication.view.CommentsFragment
import com.ahmet.yenen.myapplication.view.PhotosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeCommentsFragment(): CommentsFragment

    @ContributesAndroidInjector
    abstract fun contributePhotosFragment(): PhotosFragment
}