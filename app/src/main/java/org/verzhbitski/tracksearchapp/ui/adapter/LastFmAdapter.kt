package org.verzhbitski.tracksearchapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import org.verzhbitski.tracksearchapp.R
import org.verzhbitski.tracksearchapp.databinding.ItemLastFmTrackBinding
import org.verzhbitski.tracksearchapp.model.lastfm.entity.Track

class LastFmAdapter : RecyclerViewListAdapter<Track, LastFmAdapter.LastFmViewHolder>() {

    companion object {
        class LastFmDiffCallback(val old: List<Track>, val new: List<Track>) : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = old[oldItemPosition].mbid == new[newItemPosition].mbid

            override fun getOldListSize() = old.size

            override fun getNewListSize() = new.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                val oldItem = old[oldItemPosition]
                val newItem = new[newItemPosition]

                if (oldItem.name != newItem.name) return false

                if (oldItem.artist != oldItem.artist) return false

                if (oldItem.image != oldItem.image) return false

                return true
            }

        }
    }

    override fun update(data: ArrayList<Track>) {
        val calculateDiff = DiffUtil.calculateDiff(LastFmDiffCallback(this.data, data))
        this.data = data
        calculateDiff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastFmViewHolder {
        val inflate = DataBindingUtil.inflate<ItemLastFmTrackBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_last_fm_track,
            parent,
            false
        )

        return LastFmViewHolder(inflate)
    }

    class LastFmViewHolder(override val binding: ItemLastFmTrackBinding) : BaseViewHolder<Track>(binding) {

        override fun bind(item: Track) {
            binding.track = item
            binding.cover = item.image.first()
        }
    }
}