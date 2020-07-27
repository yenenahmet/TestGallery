package com.ahmet.yenen.myapplication.adapter

import android.webkit.WebSettings
import com.ahmet.yenen.myapplication.R
import com.ahmet.yenen.myapplication.databinding.RowPhotoBinding
import com.ahmet.yenen.myapplication.model.Photo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.yenen.ahmet.basecorelibrary.base.adapter.BaseViewBindingRecyclerViewAdapter

class PhotosAdapter : BaseViewBindingRecyclerViewAdapter<Photo, RowPhotoBinding>(
    R.layout.row_photo
) {
    override fun setBindingModel(item: Photo, binding: RowPhotoBinding, position: Int) {

        val glideUrl = GlideUrl(item.thumbnailUrl,LazyHeaders.Builder()
            .addHeader("User-Agent", WebSettings.getDefaultUserAgent(binding.root.context))
            .build())

        Glide.with(binding.root.context).load(glideUrl).fitCenter().into(binding.imgThumbnail)
        binding.tvTitle.text = item.title
    }
}