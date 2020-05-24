package com.gmail.sparknetworksgallerytest.domain.repository

import android.net.Uri
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import io.reactivex.Observable
import io.reactivex.Single

interface GalleryRepository {
    fun uploadImage(filePath: Uri): Single<ResultState<Boolean>>

    fun getAllUserImagesLinks(): Observable<ResultState<List<String>>>
}
