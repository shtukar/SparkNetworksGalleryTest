package com.gmail.sparknetworksgallerytest.presentation.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.sparknetworksgallerytest.R
import com.gmail.sparknetworksgallerytest.presentation.extentions.loadFromUrl
import kotlinx.android.synthetic.main.item_gallery_image.view.*

class ImagesListAdapter : RecyclerView.Adapter<ImagesListAdapter.AvailableProductViewHolder>() {

    private var imagesLinkList: MutableList<String> = mutableListOf()

    fun setImagesLinksList(list: List<String>) {
        imagesLinkList.clear()
        imagesLinkList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableProductViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_gallery_image, parent, false)
        return AvailableProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: AvailableProductViewHolder, position: Int) {
        holder.bind(imagesLinkList[position])
    }

    override fun getItemCount(): Int = imagesLinkList.size

    class AvailableProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(imageLink: String) {
            itemView.ivImage.loadFromUrl(imageLink)
        }
    }
}