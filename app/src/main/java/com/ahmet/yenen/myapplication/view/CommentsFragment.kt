package com.ahmet.yenen.myapplication.view

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ahmet.yenen.myapplication.R
import com.ahmet.yenen.myapplication.databinding.FragmentCommentsBinding
import com.ahmet.yenen.myapplication.viewmodel.CommentsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerFragment
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants

class CommentsFragment : BaseDaggerFragment<CommentsViewModel, FragmentCommentsBinding>(
    CommentsViewModel::class.java,
    R.layout.fragment_comments
) {


    override fun initViewModel(viewModel: CommentsViewModel) {
        binding.viewModel = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val id = arguments?.getLong("id",0L)?:0L
        setImg(arguments?.getString("imgUrl","")?:"")

        viewModel.getLiveData(id).observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.recyclerView.visibility = View.GONE
                binding.tvError.text = it.description
                when(it.status){
                    ProjectConstants.SUCCESS_STATUS->{
                        binding.recyclerView.visibility = View.VISIBLE
                        viewModel.commentsAdapter.setItems(it.data!!)
                    }
                }

                binding.progressWheel.visibility = View.GONE
                binding.progressWheel.stopSpinning()
            }
        })

    }

    override fun onBindingCreate(binding: FragmentCommentsBinding) {
        binding.tvTitle.text = arguments?.getString("title","")?:""
        binding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setImg(url:String){
        val glideUrl = GlideUrl(url, LazyHeaders.Builder()
            .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))
            .build())
        Glide.with(this).load(glideUrl).centerCrop().into(binding.imgUrl)
    }


}
