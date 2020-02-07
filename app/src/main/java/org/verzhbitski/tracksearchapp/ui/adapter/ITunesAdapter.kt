package org.verzhbitski.tracksearchapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import org.verzhbitski.tracksearchapp.R
import org.verzhbitski.tracksearchapp.databinding.ItemItunesTrackBinding
import org.verzhbitski.tracksearchapp.model.itunes.entity.Track

class ITunesAdapter: RecyclerViewListAdapter<Track, ITunesAdapter.ITunesViewHolder>() {

    companion object {
        class ITunesDiffCallback(val old: List<Track>, val new: List<Track>) : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = old[oldItemPosition].trackId == new[newItemPosition].trackId

            override fun getOldListSize() = old.size

            override fun getNewListSize() = new.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = old[oldItemPosition]
                val newItem = new[newItemPosition]

                if (oldItem.trackName != newItem.trackName) return false

                if (oldItem.artistName != newItem.artistName) return false

                if (oldItem.collectionName != newItem.collectionName) return false

                if (oldItem.releaseDate != newItem.releaseDate) return false

                if (oldItem.artworkUrl100 != newItem.artworkUrl100) return false

                return true
            }
        }
    }

    override fun update(data: ArrayList<Track>) {
        val calculateDiff = DiffUtil.calculateDiff(
            ITunesDiffCallback(
                this.data,
                data
            )
        )
        this.data = data
        calculateDiff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ITunesViewHolder {
        val inflate = DataBindingUtil.inflate<ItemItunesTrackBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_itunes_track,
            parent,
            false
        )

        return ITunesViewHolder(inflate)
    }

    class ITunesViewHolder(override val binding: ItemItunesTrackBinding): BaseViewHolder<Track>(binding) {
        override fun bind(item: Track) {
            binding.track = item
        }

    }
}