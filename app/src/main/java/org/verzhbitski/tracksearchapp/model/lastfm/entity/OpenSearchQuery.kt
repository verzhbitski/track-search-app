package org.verzhbitski.tracksearchapp.model.lastfm.entity

import com.google.gson.annotations.SerializedName

data class OpenSearchQuery(
    @SerializedName("#text")
    val text: String,
    val role: String,
    val startPage: String
)