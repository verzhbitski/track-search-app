package org.verzhbitski.tracksearchapp.model.itunes.entity

data class Response(
    val resultCount: Int,
    val results: ArrayList<Track>
)