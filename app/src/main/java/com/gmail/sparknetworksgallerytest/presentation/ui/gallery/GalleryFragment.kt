package com.gmail.sparknetworksgallerytest.presentation.ui.gallery

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.gmail.sparknetworksgallerytest.R
import com.gmail.sparknetworksgallerytest.domain.common.ResultState
import com.gmail.sparknetworksgallerytest.presentation.extentions.hide
import com.gmail.sparknetworksgallerytest.presentation.extentions.observe
import com.gmail.sparknetworksgallerytest.presentation.extentions.show
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_gallery.*
import javax.inject.Inject

class GalleryFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: GalleryViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(GalleryViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(viewModel.getUploadImageStatus(), ::onImageUpload)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
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
        pbLoading.hide()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                pbLoading.show()
                viewModel.uploadImage(result.uri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(activity, getString(R.string.default_error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onImageUpload(result: ResultState<Boolean>) {
        pbLoading.hide()
        if (result is ResultState.Success) {
            //TODO
            Toast.makeText(activity, "Image uploaded", Toast.LENGTH_SHORT).show()
        } else if (result is ResultState.Error) {
            Toast.makeText(activity, getString(R.string.default_error), Toast.LENGTH_SHORT).show()
        }

    }

}
