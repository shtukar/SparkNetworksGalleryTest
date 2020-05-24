package com.gmail.sparknetworksgallerytest.presentation.ui.gallery

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.gmail.sparknetworksgallerytest.R
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.presentation.common.BaseFragment
import com.gmail.sparknetworksgallerytest.presentation.extentions.observe
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_gallery.*
import javax.inject.Inject

class GalleryFragment : BaseFragment() {

    companion object {
        const val NUMBER_OF_COLUMNS = 2
    }

    override val layoutId: Int = R.layout.fragment_gallery

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GalleryViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(GalleryViewModel::class.java)
    }

    private lateinit var imagesListAdapter: ImagesListAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(viewModel.getUploadImageStatus(), ::onImageUpload)
        observe(viewModel.getImagesLinksStatus(), ::onImagesLinks)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ibUpload.setOnClickListener {
            context?.let { context ->
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(context, this)
            }
        }
        imagesListAdapter = ImagesListAdapter()
        layoutManager = GridLayoutManager(context, NUMBER_OF_COLUMNS)
        rvImagesList.layoutManager = layoutManager
        rvImagesList.adapter = imagesListAdapter
        viewModel.getAllUserImagesLinks()
        showLoading()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                showLoading()
                viewModel.uploadImage(result.uri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                showDefaultError()
            }
        }
    }

    private fun onImageUpload(result: ResultState<Boolean>) {
        hideLoading()
        if (result is ResultState.Error) {
            showDefaultError()
        }
    }

    private fun onImagesLinks(result: ResultState<List<String>>) {
        hideLoading()
        if (result is ResultState.Success) {
            imagesListAdapter.setImagesLinksList(result.data)
        } else if (result is ResultState.Error) {
            showDefaultError()
        }
    }

}
