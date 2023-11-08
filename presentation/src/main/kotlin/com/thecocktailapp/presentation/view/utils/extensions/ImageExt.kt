package com.thecocktailapp.presentation.view.utils.extensions

import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.mzaragozaserrano.presentation.view.utils.emptyLambda
import com.thecocktailapp.presentation.view.utils.GlideApp
import com.thecocktailapp.ui.R

fun AppCompatImageView.loadImageFromUrl(
    url: String,
    roundedCorners: Int? = null,
    onSuccess: () -> Unit = emptyLambda,
    onError: () -> Unit = emptyLambda,
) {
    var options = RequestOptions().placeholder(R.drawable.loading_img).centerCrop()
    if (roundedCorners != null) {
        options = options.transform(RoundedCorners(roundedCorners))
    }
    GlideApp.with(context)
        .load(Uri.parse(url))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(options)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean,
            ): Boolean {
                onError()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean,
            ): Boolean {
                onSuccess()
                return false
            }
        })
        .into(this)
}