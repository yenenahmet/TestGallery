package com.ahmet.yenen.myapplication.adapter

import android.annotation.SuppressLint
import com.ahmet.yenen.myapplication.R
import com.ahmet.yenen.myapplication.databinding.RowCommentBinding
import com.ahmet.yenen.myapplication.model.Comment
import com.yenen.ahmet.basecorelibrary.base.adapter.BaseViewBindingRecyclerViewAdapter

class CommentsAdapter : BaseViewBindingRecyclerViewAdapter<Comment, RowCommentBinding>(
    R.layout.row_comment
) {
    @SuppressLint("SetTextI18n")
    override fun setBindingModel(item: Comment, binding: RowCommentBinding, position: Int) {
        binding.tvName.text = "Name : ${item.name}"
        binding.tvEmail.text = "Gmail: ${item.email}"
        binding.tvBody.text = item.body
    }
}