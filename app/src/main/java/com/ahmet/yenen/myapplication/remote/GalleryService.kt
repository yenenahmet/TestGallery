package com.ahmet.yenen.myapplication.remote

import com.ahmet.yenen.myapplication.model.Comment
import com.ahmet.yenen.myapplication.model.Photo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GalleryService {


    @GET("photos")
    fun getPhotos(): Single<List<Photo>>

    @GET("comments?")
    fun getComments(@Query("postId") id:Long): Single<List<Comment>>

}