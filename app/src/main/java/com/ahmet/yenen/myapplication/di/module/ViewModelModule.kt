package com.ahmet.yenen.myapplication.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ahmet.yenen.myapplication.di.scope.ViewModelKey
import com.ahmet.yenen.myapplication.viewmodel.CommentsViewModel
import com.ahmet.yenen.myapplication.viewmodel.MainViewModel
import com.ahmet.yenen.myapplication.viewmodel.PhotosViewModel
import com.yenen.ahmet.basecorelibrary.base.di.factory.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotosViewModel::class)
    internal abstract fun bindPhotosViewModel(viewModel: PhotosViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    internal abstract fun bindCommentsViewModel(viewModel: CommentsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(appViewModelFactory: AppViewModelFactory): ViewModelProvider.Factory
}