/*
 * FragmentMatch.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 8:11 AM
 * Last modified 10/24/18 8:09 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidnetworking.error.ANError
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh
import tampan.kucingapes.submission4kotlindicoding.MainContract
import tampan.kucingapes.submission4kotlindicoding.adapter.MatchAdapter
import tampan.kucingapes.submission4kotlindicoding.model.Match
import tampan.kucingapes.submission4kotlindicoding.presenter.GetApi
import tampan.kucingapes.submission4kotlindicoding.presenter.ApiPresenter

class FragmentMatch : BaseFragment(), MainContract.MainView {

    private var type: Int = 0

    private var matchAdapter: MatchAdapter? = null
    private var dataList: MutableList<Match> = mutableListOf()

    private lateinit var presenter: ApiPresenter

    companion object {
        fun newInstance(type: Int): FragmentMatch {
            val fragmentMatch = FragmentMatch()
            val bundle = Bundle()
            bundle.putInt("type", type)
            fragmentMatch.arguments = bundle
            return fragmentMatch
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getInt("type")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val ui = context?.let {
            AnkoContext.create(it)
        }?.let {
            createView(it)
        }

        progressOn()
        presenter = ApiPresenter(this, GetApi(type))
        presenter.requestData()

        return ui
    }

    override fun progressOn() {
        swipeRefresh.isRefreshing = true
        swipeRefresh.onRefresh {
            presenter.requestData()
            dataList.clear()
            matchAdapter?.notifyDataSetChanged()
        }
    }

    override fun progressOff() {
        swipeRefresh.isRefreshing = false
    }

    override fun setDataList(dataList: MutableList<Match>) {
        this.dataList = dataList
        matchAdapter = MatchAdapter(this.dataList)
        listMatch.adapter = matchAdapter
    }

    override fun onFailedResponses(anError: ANError) {
        error { "my_error ${anError.errorBody}" }
    }

}