package com.gmail.sparknetworksgallerytest.domain.usecase

import android.net.Uri
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.domain.repository.GalleryRepository
import io.reactivex.Single

class GalleryUseCaseImpl(private val repository: GalleryRepository) : GalleryUseCase {

    override fun uploadImage(filePath: Uri): Single<ResultState<Boolean>> {
        return repository.uploadImage(filePath)
    }
}
