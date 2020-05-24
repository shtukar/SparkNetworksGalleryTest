package com.gmail.sparknetworksgallerytest.presentation.ui.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.sparknetworksgallerytest.R
import com.gmail.sparknetworksgallerytest.presentation.common.BaseDiffCalculator
import com.gmail.sparknetworksgallerytest.presentation.common.BaseRecyclerAdapter
import com.gmail.sparknetworksgallerytest.presentation.common.BaseRecyclerHolder
import com.gmail.sparknetworksgallerytest.presentation.extentions.loadFromUrl
import kotlinx.android.synthetic.main.item_gallery_image.view.*

class ImagesListAdapter(context: Context) : BaseRecyclerAdapter<String>(context) {

    override val diffCalculator: BaseDiffCalculator<String>? = BaseDiffCalculator()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_gallery_image, parent, false)
        return BaseRecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        holder.itemView.ivImage.loadFromUrl((items[position]))
    }
}