package org.verzhbitski.tracksearchapp.model.lastfm.entity

data class Track(
    val name: String,
    val artist: String,
    val url: String,
    val streamable: String,
    val listeners: String,
    val image: List<Image>,
    val mbid: String
)
