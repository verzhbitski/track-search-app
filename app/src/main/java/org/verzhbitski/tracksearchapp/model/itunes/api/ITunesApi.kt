package org.verzhbitski.tracksearchapp.model.itunes.api

import org.verzhbitski.tracksearchapp.model.itunes.entity.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApi {

    @GET("search?entity=song")
    fun search(
        @Query("term") term: String,
        @Query("limit") limit: Int = 20
    ): Call<Response>
}