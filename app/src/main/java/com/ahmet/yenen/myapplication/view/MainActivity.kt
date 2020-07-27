package com.ahmet.yenen.myapplication.view


import com.ahmet.yenen.myapplication.R
import com.ahmet.yenen.myapplication.databinding.ActivityMainBinding
import com.ahmet.yenen.myapplication.viewmodel.MainViewModel
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerActivity

class MainActivity :
    BaseDaggerActivity<MainViewModel, ActivityMainBinding>(
        MainViewModel::class.java,
        R.layout.activity_main
    ) {


    override fun initViewModel(viewModel: MainViewModel) {
        binding.viewModel = viewModel
    }

}
