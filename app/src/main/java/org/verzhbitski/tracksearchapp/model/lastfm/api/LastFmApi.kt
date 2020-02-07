package org.verzhbitski.tracksearchapp.model.lastfm.api

import org.verzhbitski.tracksearchapp.utils.LAST_FM_API_KEY
import org.verzhbitski.tracksearchapp.model.lastfm.entity.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApi {

    @GET("?method=track.search&api_key=$LAST_FM_API_KEY&format=json")
    fun search(
        @Query("track") query: String,
        @Query("page") page: Int = 0,
        @Query("limit") limit: Int = 20
    ): Call<Response>
}