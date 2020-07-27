package com.ahmet.yenen.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmet.yenen.myapplication.adapter.CommentsAdapter
import com.ahmet.yenen.myapplication.model.Comment
import com.ahmet.yenen.myapplication.model.Photo
import com.ahmet.yenen.myapplication.remote.GalleryService
import com.yenen.ahmet.basecorelibrary.base.model.LiveDataResponseModel
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants
import com.yenen.ahmet.basecorelibrary.base.viewmodel.BaseRxViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommentsViewModel @Inject constructor(private val service: GalleryService) :
    BaseRxViewModel() {

    val commentsAdapter = CommentsAdapter()

    private var photoLiveData:
            MutableLiveData<LiveDataResponseModel<List<Comment>>>? = null


    fun getLiveData(id: Long): LiveData<LiveDataResponseModel<List<Comment>>> {
        if (photoLiveData == null) {
            getComments(id)
            photoLiveData = MutableLiveData()
        }
        return photoLiveData!!
    }


    private fun getComments(id: Long) {
        addDisposable(
            service.getComments(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (it.isNullOrEmpty()) {
                        photoLiveData?.value = LiveDataResponseModel(
                            null,
                            ProjectConstants.SUCCESS_STATUS_EMPTY_DATA,
                            "Veri bulunamadı!"
                        )
                    } else {
                        photoLiveData?.value = LiveDataResponseModel(
                            it,
                            ProjectConstants.SUCCESS_STATUS,
                            ""
                        )
                    }
                }, {
                    photoLiveData?.value = LiveDataResponseModel(
                        null,
                        ProjectConstants.ERROR_STATUS,
                        it.toString()
                    )
                })
        )
    }
}