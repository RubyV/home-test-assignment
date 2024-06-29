package com.example.home_test_travel.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chad.library.adapter4.BaseQuickAdapter
import com.example.home_test_travel.common.extension.load
import com.example.home_test_travel.data.attractions.AttractionsData
import com.example.home_test_travel.data.category.Category
import com.example.home_test_travel.databinding.ItemAttractionBinding
import com.example.home_test_travel.databinding.ItemCategoryBinding


class CategoryAdapter() : BaseQuickAdapter<Category, CategoryAdapter.ViewHolder>() {

    class ViewHolder(
        parent: ViewGroup,
        val binding: ItemCategoryBinding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int): ViewHolder {
        // 返回一个 ViewHolder
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, item: Category?) {
        val tvCategoryTitle = holder.binding.tvCategoryTitle
        val ivCategory = holder.binding.imageViewCategoryThumbnail

        tvCategoryTitle.text = item?.categoryTitle

        if (item?.imageUrl != null) {
            ivCategory.setImageDrawable(context.getDrawable(item.imageUrl))
        }
    }
}