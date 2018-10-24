/*
 * BaseFragment.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 9:39 AM
 * Last modified 10/24/18 9:39 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import tampan.kucingapes.submission4kotlindicoding.R

open class BaseFragment : Fragment(), AnkoComponent<Context>, AnkoLogger {

    lateinit var swipeRefresh: SwipeRefreshLayout
    lateinit var listMatch: RecyclerView
    lateinit var emptyView: LinearLayout

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)

            swipeRefresh = swipeRefreshLayout {
                id = R.id.swipe_refresh
                setColorSchemeResources(
                    R.color.colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                listMatch = recyclerView {
                    id = R.id.list_match
                    lparams(matchParent, matchParent)
                    topPadding = dip(6)
                    bottomPadding = dip(6)
                    clipToPadding = false

                    layoutManager = LinearLayoutManager(context)
                }
            }.lparams(matchParent, matchParent)

            emptyView = verticalLayout {
                id = R.id.empty_view
                visibility = View.GONE
                gravity = Gravity.CENTER

                relativeLayout {
                    imageView {
                        image = ContextCompat.getDrawable(context,
                            R.drawable.ic_favorite
                        )
                    }.lparams(dip(100), dip(100)) {
                        margin = dip(20)
                    }

                    imageView {
                        image = ContextCompat.getDrawable(context,
                            R.drawable.line
                        )
                    }.lparams(dip(100), dip(100)) {
                        margin = dip(20)
                    }
                }.lparams(wrapContent, wrapContent)

            }.lparams(matchParent, matchParent)
        }
    }
}