package org.verzhbitski.tracksearchapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.verzhbitski.tracksearchapp.ui.fragment.SearchFragment
import org.verzhbitski.tracksearchapp.ui.fragment.ITunesFragment
import org.verzhbitski.tracksearchapp.ui.fragment.LastFmFragment

class MainAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = ArrayList<SearchFragment<*>>()

    init {
        fragments.add(ITunesFragment.newInstance())
        fragments.add(LastFmFragment.newInstance())
    }

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]

    fun getPageTitle(position: Int) = fragments[position].getTitle()

    fun search(query: String) {
        fragments.forEach {
            it.search(query)
        }
    }


}