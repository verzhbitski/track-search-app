package org.verzhbitski.tracksearchapp.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewListAdapter<T, V: RecyclerViewListAdapter.BaseViewHolder<T>> : RecyclerView.Adapter<V>() {

    protected var data = ArrayList<T>()

    open fun update(data: ArrayList<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: V, position: Int) {
        holder.bind(data[position])
    }

    abstract class BaseViewHolder<T>(open val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        abstract fun bind(item: T)
    }
}