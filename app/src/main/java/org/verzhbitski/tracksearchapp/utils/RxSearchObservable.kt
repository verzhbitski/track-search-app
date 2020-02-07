package org.verzhbitski.tracksearchapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import io.reactivex.Observable

class RxSearchObservable {

    companion object {

        fun fromView(searchView: EditText): Observable<String> {

//            val subject = PublishSubject.create<String>()
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    newText?.let { subject.onNext(it.trim()) }
//                    return true
//                }
//
//            })
//
//            return subject

//            return Observable.create {
//                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                    override fun onQueryTextSubmit(query: String?): Boolean {
//                        return true
//                    }
//
//                    override fun onQueryTextChange(newText: String?): Boolean {
//                        newText?.let { it1 -> it.onNext(it1) }
//                        return true
//                    }
//                }
//
//                )
//            }

            return Observable.create {
                searchView.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        it.onNext(s.toString())
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {

                    }

                })
            }
        }
    }
}