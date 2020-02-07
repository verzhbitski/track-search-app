package org.verzhbitski.tracksearchapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.verzhbitski.tracksearchapp.R
import org.verzhbitski.tracksearchapp.databinding.FragmentMainBinding
import org.verzhbitski.tracksearchapp.ui.adapter.MainAdapter
import org.verzhbitski.tracksearchapp.utils.RxSearchObservable
import java.util.concurrent.TimeUnit

class MainFragment: BaseFragment<FragmentMainBinding>() {

    private lateinit var mainAdapter: MainAdapter

    private val compositeDisposable = CompositeDisposable()

    override fun getLayoutId() = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        compositeDisposable.clear()
    }

    private fun initView() {
        mainAdapter = MainAdapter(this)
        binding.viewPager.apply {
            adapter = mainAdapter
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = mainAdapter.getPageTitle(position)
        }.attach()

        compositeDisposable.add(RxSearchObservable.fromView(binding.search)
            .debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mainAdapter.search(it)
            }, {

            }))
    }
}