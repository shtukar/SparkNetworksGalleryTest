package com.gmail.sparknetworksgallerytest.presentation.ui.gallery

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.domain.usecase.GalleryUseCase
import com.gmail.sparknetworksgallerytest.presentation.common.BaseViewModel
import javax.inject.Inject

class GalleryViewModel @Inject constructor(private val galleryUseCase: GalleryUseCase) : BaseViewModel() {

    private val uploadImageStatus by lazy { MutableLiveData<ResultState<Boolean>>() }

    fun getUploadImageStatus(): LiveData<ResultState<Boolean>> = uploadImageStatus

    fun uploadImage(filePath: Uri) {
        galleryUseCase.uploadImage(filePath)
                .subscribe { result: ResultState<Boolean> ->
                    uploadImageStatus.postValue(result)
                }
                .track()
    }
}