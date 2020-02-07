package org.verzhbitski.tracksearchapp.viewmodel

import org.verzhbitski.tracksearchapp.model.itunes.api.ITunesApi
import org.verzhbitski.tracksearchapp.model.itunes.entity.Response
import org.verzhbitski.tracksearchapp.model.itunes.entity.Track
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ITunesViewModel : BaseViewModel<Track, ITunesApi>() {

    var limit = 20

    override var api = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ITunesApi::class.java)

    override fun search(query: String) {
        limit = 20
        api.search(query).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                message.postValue(t.message)
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                response.body()?.results?.let {
                    tracks.postValue(it)
                    return
                }

                tracks.postValue(ArrayList())
            }
        })
    }

    override fun fetchMore(query: String) {
        limit += 20
        api.search(query, limit).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                message.postValue(t.message)
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                response.body()?.results?.let {
                    tracks.postValue(it)
                    return
                }

                tracks.postValue(ArrayList())
            }

        })
    }
}