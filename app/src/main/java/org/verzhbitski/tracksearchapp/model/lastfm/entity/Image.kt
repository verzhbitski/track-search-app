package org.verzhbitski.tracksearchapp.model.lastfm.entity

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text")
    val url: String,
    val size: String
)
