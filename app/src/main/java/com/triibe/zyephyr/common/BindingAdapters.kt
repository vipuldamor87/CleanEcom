package com.triibe.zyephyr.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, s: String?) {

    val options = RequestOptions.placeholderOf(androidx.appcompat.R.drawable.notification_template_icon_bg).error(
        com.google.android.material.R.drawable.mtrl_ic_error)
    Glide.with(view).setDefaultRequestOptions(options).load(s ?: "").into(view)
}