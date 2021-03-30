package com.example.module_base.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.example.module_base.R


object BindingUtil {

    @JvmStatic
    @BindingAdapter(value = ["isVisible"], requireAll = false)
    fun isVisible(view: View, visibility: Boolean) {
        if (visibility) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["url"], requireAll = false)
    fun loadUrl(imageView: ImageView, url: String?) {
        val options = RequestOptions()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.mipmap.default_project_img)
        GlideApp.with(imageView.context).load(url).apply(options).into(imageView)
    }

}