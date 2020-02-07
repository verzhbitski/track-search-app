package org.verzhbitski.tracksearchapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import org.verzhbitski.tracksearchapp.R
import org.verzhbitski.tracksearchapp.databinding.FragmentTrackListBinding
import org.verzhbitski.tracksearchapp.ui.adapter.RecyclerViewListAdapter
import org.verzhbitski.tracksearchapp.viewmodel.BaseViewModel

abstract class SearchFragment<T> : BaseFragment<FragmentTrackListBinding>() {

    abstract val viewModel: BaseViewModel<T, *>

    abstract val trackAdapter: RecyclerViewListAdapter<T, out RecyclerViewListAdapter.BaseViewHolder<T>>

    protected var cachedQuery = ""

    abstract fun getTitle(): String

    fun search(query: String) {
        if (isAdded) viewModel.search(query)

        cachedQuery = query
    }

    override fun getLayoutId() = R.layout.fragment_track_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservables()
    }

    private fun initView() {
        binding.trackRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = trackAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val llm = recyclerView.layoutManager as LinearLayoutManager
                    if (llm.findLastCompletelyVisibleItemPosition() == adapter!!.itemCount - 5) viewModel.fetchMore(cachedQuery)
                }
            })
        }
        if (cachedQuery.isNotEmpty()) viewModel.search(cachedQuery)
    }

    private fun initObservables() {
        viewModel.message.observe(viewLifecycleOwner) {
            showMessage(it)
        }

        viewModel.tracks.observe(viewLifecycleOwner) {
            trackAdapter.update(it)
            binding.noMatchesText.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    fun showMessage(@StringRes stringId: Int) {
        showMessage(getString(stringId))
    }

    fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}