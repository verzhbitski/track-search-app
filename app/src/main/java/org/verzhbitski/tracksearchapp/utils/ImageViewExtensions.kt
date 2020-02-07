package org.verzhbitski.tracksearchapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    ImageLoader.load(view, url)
}
