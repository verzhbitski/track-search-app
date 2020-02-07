package org.verzhbitski.tracksearchapp.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import org.verzhbitski.tracksearchapp.R

class ImageLoader {

    companion object {

        lateinit var queue: RequestQueue

        fun init(context: Context) {
            queue = Volley.newRequestQueue(context)
        }

        fun load(view: ImageView, url: String) {
            view.setImageResource(R.drawable.ic_album)

            val request = ImageRequest(url,
                Response.Listener<Bitmap> { response ->
                    response?.let {
                        view.setImageBitmap(it)
                    }
                }, 0, 0,
                ImageView.ScaleType.CENTER_CROP,
                Bitmap.Config.ARGB_8888,
                Response.ErrorListener {

                })

            queue.add(request)
        }
    }
}