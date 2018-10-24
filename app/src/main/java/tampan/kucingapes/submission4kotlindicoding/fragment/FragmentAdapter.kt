/*
 * FragmentAdapter.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 6:28 AM
 * Last modified 10/24/18 6:28 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val pager: MutableList<Fragment> = mutableListOf()

    override fun getItem(p0: Int): Fragment {
        return pager[p0]
    }

    override fun getCount(): Int {
        return pager.size
    }

    fun addFragment(fragment: Fragment) {
        pager.add(fragment)
    }
}