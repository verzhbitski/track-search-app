package org.verzhbitski.tracksearchapp.ui.fragment

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import org.verzhbitski.tracksearchapp.model.lastfm.entity.Track
import org.verzhbitski.tracksearchapp.ui.adapter.LastFmAdapter
import org.verzhbitski.tracksearchapp.viewmodel.LastFmViewModel

class LastFmFragment : SearchFragment<Track>() {

    companion object {
        fun newInstance() = LastFmFragment().apply {
            arguments = bundleOf()
        }
    }

    override val viewModel by viewModels<LastFmViewModel>()

    override val trackAdapter = LastFmAdapter()

    override fun getTitle() = "Last.fm"
}