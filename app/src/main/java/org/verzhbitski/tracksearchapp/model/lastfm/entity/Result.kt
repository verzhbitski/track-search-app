package org.verzhbitski.tracksearchapp.model.lastfm.entity

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("opensearch:Query")
    val openSearchQuery: OpenSearchQuery,
    @SerializedName("opensearch:totalResults")
    val openSearchTotalResults: String,
    @SerializedName("opensearch:startIndex")
    val openSearchStartIndex: String,
    @SerializedName("opensearch:itemsPerPage")
    val openSearchItemsPerPage: String,
    val trackmatches: Trackmatches
)