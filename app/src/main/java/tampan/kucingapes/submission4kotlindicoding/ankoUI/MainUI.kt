/*
 * MainUI.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 6:30 AM
 * Last modified 10/24/18 6:26 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.ankoUI

import android.os.Build
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.support.v4.viewPager
import tampan.kucingapes.submission4kotlindicoding.fragment.FragmentAdapter
import tampan.kucingapes.submission4kotlindicoding.activity.MainActivity
import tampan.kucingapes.submission4kotlindicoding.R

class MainUI (private val fragmentAdapter: FragmentAdapter) : AnkoComponent<MainActivity> {
    private lateinit var mViewPager: ViewPager
    private lateinit var mBottomNavigationView: BottomNavigationView

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        relativeLayout {
            lparams(matchParent)

            mViewPager = viewPager {
                id = R.id.pager
                adapter = fragmentAdapter

                addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(p0: Int) {}

                    override fun onPageScrolled(p0: Int, p1: Float, p2: Int){}

                    override fun onPageSelected(p0: Int) {
                        when (p0) {
                            0 -> mBottomNavigationView.selectedItemId =
                                    R.id.last_match
                            1 -> mBottomNavigationView.selectedItemId =
                                    R.id.next_match
                            2 -> mBottomNavigationView.selectedItemId =
                                    R.id.favorites_match
                        }
                    }
                })

            }.lparams(matchParent, matchParent) {
                above(R.id.bottom_layout)
            }

            view {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    background = ContextCompat.getDrawable(context,
                        R.drawable.shadow
                    )
                }
            }.lparams(matchParent, dip(5)) {
                above(R.id.bottom_layout)
            }

            mBottomNavigationView = bottomNavigationView {
                id = R.id.bottom_layout
                itemBackgroundResource = android.R.color.white
                inflateMenu(R.menu.bottom_menu)

                setOnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.last_match -> mViewPager.setCurrentItem(0, true)
                        R.id.next_match -> mViewPager.setCurrentItem(1, true)
                        R.id.favorites_match -> mViewPager.setCurrentItem(2, true)
                    }
                    true
                }

            }.lparams(matchParent, wrapContent) {
                alignParentBottom()
            }
        }
    }
}