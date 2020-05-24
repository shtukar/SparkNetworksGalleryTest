package com.gmail.sparknetworksgallerytest.gallery

import android.net.Uri
import com.gmail.sparknetworksgallerytest.BaseTest
import com.gmail.sparknetworksgallerytest.TEST_CODE_ERROR
import com.gmail.sparknetworksgallerytest.TEST_MESSAGE_ERROR
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.domain.entity.ErrorState
import com.gmail.sparknetworksgallerytest.domain.usecase.AuthUseCase
import com.gmail.sparknetworksgallerytest.domain.usecase.GalleryUseCase
import com.gmail.sparknetworksgallerytest.presentation.ui.gallery.GalleryViewModel
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito

class GalleryViewModelTest : BaseTest() {

    @Mock
    lateinit var authUseCase: AuthUseCase

    @Mock
    lateinit var galleryUseCase: GalleryUseCase

    @Mock
    lateinit var fileUri: Uri

    @InjectMocks
    lateinit var viewModel: GalleryViewModel

    @Test
    fun `upload image`() {
        val resultSuccess: ResultState<Boolean> = ResultState.Success(true)

        Mockito.`when`(galleryUseCase.uploadImage(fileUri)).thenReturn(Single.just(resultSuccess))
        viewModel.uploadImage(fileUri)
        Assert.assertEquals(resultSuccess, viewModel.getUploadImageStatus().value)
    }

    @Test
    fun `upload image error`() {
        val resultError: ResultState<Boolean> =
                ResultState.Error(ErrorState(TEST_CODE_ERROR, TEST_MESSAGE_ERROR), null)

        Mockito.`when`(galleryUseCase.uploadImage(fileUri)).thenReturn(Single.just(resultError))
        viewModel.uploadImage(fileUri)
        Assert.assertEquals(resultError, viewModel.getUploadImageStatus().value)
    }

    @Test
    fun `get all user images links`() {
        val resultSuccess: ResultState<List<String>> = ResultState.Success(listOf())

        Mockito.`when`(galleryUseCase.getAllUserImagesLinks()).thenReturn(Observable.just(resultSuccess))
        viewModel.getAllUserImagesLinks()
        Assert.assertEquals(resultSuccess, viewModel.getImagesLinksStatus().value)
    }

    @Test
    fun `get all user images links error`() {
        val resultError: ResultState<List<String>> =
                ResultState.Error(ErrorState(TEST_CODE_ERROR, TEST_MESSAGE_ERROR), null)

        Mockito.`when`(galleryUseCase.getAllUserImagesLinks()).thenReturn(Observable.just(resultError))
        viewModel.getAllUserImagesLinks()
        Assert.assertEquals(resultError, viewModel.getImagesLinksStatus().value)
    }

    @Test
    fun `log out`() {
        val resultSuccess: ResultState<Boolean> = ResultState.Success(true)

        Mockito.`when`(authUseCase.logOut()).thenReturn(Single.just(resultSuccess))
        viewModel.logOut()
        Assert.assertEquals(resultSuccess, viewModel.getLogOutStatus().value)
    }

    @Test
    fun `log out error`() {
        val resultError: ResultState<Boolean> =
                ResultState.Error(ErrorState(TEST_CODE_ERROR, TEST_MESSAGE_ERROR), null)

        Mockito.`when`(authUseCase.logOut()).thenReturn(Single.just(resultError))
        viewModel.logOut()
        Assert.assertEquals(resultError, viewModel.getLogOutStatus().value)
    }
}