package org.verzhbitski.tracksearchapp.viewmodel

import android.util.Log
import org.verzhbitski.tracksearchapp.model.lastfm.api.LastFmApi
import org.verzhbitski.tracksearchapp.model.lastfm.entity.Response
import org.verzhbitski.tracksearchapp.model.lastfm.entity.Track
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LastFmViewModel : BaseViewModel<Track, LastFmApi>() {

    companion object {
        private val TAG = LastFmViewModel::class.java.simpleName
    }

    private var lastPage = 1

    override var api = Retrofit.Builder()
        .baseUrl("http://ws.audioscrobbler.com/2.0/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LastFmApi::class.java)

    override fun search(query: String) {
        if (query.isEmpty()) {
            tracks.postValue(ArrayList())
            return
        }

        lastPage = 1
        api.search(query).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                message.postValue(t.message)
                Log.e(TAG, Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                response.body()?.results?.trackmatches?.track?.let {
                    tracks.postValue(it)
                    return
                }

                tracks.postValue(ArrayList())
            }
        })
    }

    override fun fetchMore(query: String) {
        Log.d(TAG, "fetching more: ${query}, ${lastPage}")

        if (loading || query.isEmpty()) return

        Log.d(TAG, "fetch")

        loading = true

        lastPage++
        api.search(query, lastPage).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>, t: Throwable) {
                message.postValue(t.message)
                loading = false
                Log.e(TAG, Log.getStackTraceString(t))
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                response.body()?.results?.trackmatches?.track?.let {
                    loading = false
                    tracks.value?.addAll(it)
                    tracks.postValue(tracks.value)
                }
            }
        })
    }
}