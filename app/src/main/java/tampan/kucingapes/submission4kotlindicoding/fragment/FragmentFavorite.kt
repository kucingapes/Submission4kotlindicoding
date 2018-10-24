/*
 * FragmentFavorite.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 8:44 AM
 * Last modified 10/24/18 8:44 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.onRefresh
import tampan.kucingapes.submission4kotlindicoding.MainContract
import tampan.kucingapes.submission4kotlindicoding.adapter.MatchFavoriteAdapter
import tampan.kucingapes.submission4kotlindicoding.model.MatchFavorite
import tampan.kucingapes.submission4kotlindicoding.presenter.SqlPresenter
import tampan.kucingapes.submission4kotlindicoding.presenter.GetSql

class FragmentFavorite : BaseFragment(), MainContract.FavoriteView {

    private var matchAdapter: MatchFavoriteAdapter? = null
    private var dataList: List<MatchFavorite> = ArrayList()

    private lateinit var presenter: SqlPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val ui = context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }

        progressOn()
        presenter = SqlPresenter(this, GetSql(context))
        presenter.requestData()

        if (dataList.isEmpty()) {
            emptyView.visibility = View.VISIBLE
        } else {
            emptyView.visibility = View.GONE
        }

        return ui
    }

    override fun progressOn() {
        swipeRefresh.isRefreshing = true
        swipeRefresh.onRefresh {
            presenter.requestData()
            matchAdapter?.notifyDataSetChanged()
        }
    }

    override fun progressOff() {
        swipeRefresh.isRefreshing = false
    }

    override fun setDataList(dataList: List<MatchFavorite>) {
        this.dataList = dataList
        matchAdapter = MatchFavoriteAdapter(dataList)
        listMatch.adapter = matchAdapter
    }
}