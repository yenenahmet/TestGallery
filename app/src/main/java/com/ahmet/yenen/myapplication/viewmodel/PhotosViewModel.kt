package com.ahmet.yenen.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ahmet.yenen.myapplication.adapter.PhotosAdapter
import com.ahmet.yenen.myapplication.model.Photo
import com.ahmet.yenen.myapplication.remote.GalleryService
import com.yenen.ahmet.basecorelibrary.base.model.LiveDataResponseModel
import com.yenen.ahmet.basecorelibrary.base.utility.ProjectConstants
import com.yenen.ahmet.basecorelibrary.base.viewmodel.BaseRxViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotosViewModel @Inject constructor(private val service: GalleryService) : BaseRxViewModel() {

    val adapter = PhotosAdapter()


    private val photoLiveData by lazy {
        getPhotos()
        MutableLiveData<LiveDataResponseModel<List<Photo>>>()
    }

    fun getLiveData(): LiveData<LiveDataResponseModel<List<Photo>>> {
        return photoLiveData
    }


    private fun getPhotos(){
        addDisposable(
            service.getPhotos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if(it.isNullOrEmpty()){
                        photoLiveData.value = LiveDataResponseModel(
                            null,
                            ProjectConstants.SUCCESS_STATUS_EMPTY_DATA,
                            "Veri bulunamadı!"
                        )
                    }else{
                        photoLiveData.value = LiveDataResponseModel(
                            it,
                            ProjectConstants.SUCCESS_STATUS,
                            ""
                        )
                    }
                },{
                    photoLiveData.value = LiveDataResponseModel(
                        null,
                        ProjectConstants.ERROR_STATUS,
                        it.toString()
                    )
                })
        )
    }
}