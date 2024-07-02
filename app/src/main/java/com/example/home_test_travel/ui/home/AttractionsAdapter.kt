package com.example.home_test_travel.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter
import com.example.home_test_travel.R
import com.example.home_test_travel.common.extension.load
import com.example.home_test_travel.data.attractions.AttractionsData
import com.example.home_test_travel.databinding.ItemAttractionBinding


class AttractionsAdapter(
    val callback: AttractionAdapterListener
) : BaseQuickAdapter<AttractionsData, AttractionsAdapter.ViewHolder>() {

    interface AttractionAdapterListener {
        fun onAttractionClick(attractionsData: AttractionsData)
    }

    class ViewHolder(
        parent: ViewGroup,
        val binding: ItemAttractionBinding = ItemAttractionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, item: AttractionsData?) {
        val tvAttractionTitle = holder.binding.tvAttractionTitle
        val tvAttractionDesc = holder.binding.tvAttractionSubTitle
        val cardAttraction = holder.binding.card
        val ivAttractionThumbnail = holder.binding.ivAttractionThumbnail


        tvAttractionTitle.text = item?.name ?: ""
        tvAttractionDesc.text = item?.introduction ?: ""
        cardAttraction.setOnClickListener {
            callback.onAttractionClick(item!!)
        }
        if((item?.images?.size ?: 0) > 0)
        {
            ivAttractionThumbnail.load(item?.images?.get(0)?.src?: "", context)
        }
        else
        {
            ivAttractionThumbnail.setImageDrawable(context.getDrawable(R.drawable.ic_loading))
        }
    }
}