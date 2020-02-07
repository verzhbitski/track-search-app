package org.verzhbitski.tracksearchapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T, V> : ViewModel() {

    var tracks = MutableLiveData<ArrayList<T>>()

    var message = MutableLiveData<String>()

    var loading = false

    protected abstract var api: V

    abstract fun search(query: String)

    abstract fun fetchMore(query: String)
}