package com.gmail.sparknetworksgallerytest.presentation.ui.gallery

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.domain.usecase.AuthUseCase
import com.gmail.sparknetworksgallerytest.domain.usecase.GalleryUseCase
import com.gmail.sparknetworksgallerytest.presentation.common.BaseViewModel
import javax.inject.Inject

class GalleryViewModel @Inject constructor(private val galleryUseCase: GalleryUseCase,
                                           private val authUseCase: AuthUseCase) : BaseViewModel() {

    private val uploadImageStatus by lazy { MutableLiveData<ResultState<Boolean>>() }

    fun getUploadImageStatus(): LiveData<ResultState<Boolean>> = uploadImageStatus

    private val imagesLinksStatus by lazy { MutableLiveData<ResultState<List<String>>>() }

    fun getImagesLinksStatus(): LiveData<ResultState<List<String>>> = imagesLinksStatus

    private val logOutStatus by lazy { MutableLiveData<ResultState<Boolean>>() }

    fun getLogOutStatus(): LiveData<ResultState<Boolean>> = logOutStatus

    fun uploadImage(filePath: Uri) {
        galleryUseCase.uploadImage(filePath)
                .subscribe { result: ResultState<Boolean> ->
                    uploadImageStatus.postValue(result)
                }
                .track()
    }

    fun getAllUserImagesLinks() {
        galleryUseCase.getAllUserImagesLinks()
                .subscribe { result: ResultState<List<String>> ->
                    imagesLinksStatus.postValue(result)
                }
                .track()
    }

    fun logOut() {
        authUseCase.logOut()
                .subscribe { result: ResultState<Boolean> ->
                    onCleared()
                    logOutStatus.postValue(result)
                }
                .track()
    }
}