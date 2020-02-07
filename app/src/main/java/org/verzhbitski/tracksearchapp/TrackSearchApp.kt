package org.verzhbitski.tracksearchapp

import android.app.Application
import org.verzhbitski.tracksearchapp.utils.ImageLoader

class TrackSearchApp : Application() {

    override fun onCreate() {
        super.onCreate()

        ImageLoader.init(applicationContext)
    }
}