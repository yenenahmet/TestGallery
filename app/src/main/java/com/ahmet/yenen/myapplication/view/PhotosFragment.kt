package com.ahmet.yenen.myapplication.view

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ahmet.yenen.myapplication.R
import com.ahmet.yenen.myapplication.databinding.FragmentPhotosBinding
import com.ahmet.yenen.myapplication.databinding.RowPhotoBinding
import com.ahmet.yenen.myapplication.model.Photo
import com.ahmet.yenen.myapplication.viewmodel.PhotosViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.yenen.ahmet.basecorelibrary.base.adapter.BaseViewBindingRecyclerViewAdapter
import com.yenen.ahmet.basecorelibrary.base.ui.BaseDaggerFragment
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants

class PhotosFragment : BaseDaggerFragment<PhotosViewModel, FragmentPhotosBinding>(
    PhotosViewModel::class.java,
    R.layout.fragment_photos
), BaseViewBindingRecyclerViewAdapter.ClickListener<Photo, RowPhotoBinding> {


    override fun initViewModel(viewModel: PhotosViewModel) {
        binding.viewModel = viewModel
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.recyclerView.visibility = View.GONE
                binding.tvError.text = it.description

                when (it.status) {

                    ProjectConstants.SUCCESS_STATUS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        it.data!![0].url?.let {
                            setImg(it)
                        }
                        viewModel.adapter.setItems(it.data!!)
                    }
                }

                binding.progressWheel.visibility = View.GONE
                binding.progressWheel.stopSpinning()
            }
        })

        viewModel.adapter.setListener(this)
    }


    private fun setImg(url: String) {
        val glideUrl = GlideUrl(
            url, LazyHeaders.Builder()
                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))
                .build()
        )

        Glide.with(binding.root.context).load(glideUrl).centerCrop().into(binding.imgUrl)
    }

    override fun onItemClick(item: Photo, position: Int, rowBinding: RowPhotoBinding) {
        val bundle = Bundle().apply {
            putString("imgUrl", item.url)
            putLong("id", item.id ?: 0)
            putString("title", item.title)
        }
        findNavController().navigate(R.id.action_photosFragment_to_commentsFragment, bundle)
    }


}
