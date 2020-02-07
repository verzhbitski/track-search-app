package org.verzhbitski.tracksearchapp.ui.fragment

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import org.verzhbitski.tracksearchapp.model.itunes.entity.Track
import org.verzhbitski.tracksearchapp.ui.adapter.ITunesAdapter
import org.verzhbitski.tracksearchapp.viewmodel.ITunesViewModel

class ITunesFragment : SearchFragment<Track>() {

    companion object {
        fun newInstance() = ITunesFragment().apply {
            arguments = bundleOf()
        }
    }

    override val trackAdapter = ITunesAdapter()

    override val viewModel by viewModels<ITunesViewModel>()

    override fun getTitle() = "iTunes"
}