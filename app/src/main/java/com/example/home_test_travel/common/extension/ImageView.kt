package com.example.home_test_travel.common.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.Target
import com.example.home_test_travel.R


fun ImageView.load(path: String, context: Context) {
    Glide.with(context)
        .asBitmap()
        .dontAnimate()
        .load(path)
        .placeholder(R.drawable.ic_loading)
        .error(R.drawable.ic_no_image)
        .into(this)

}

