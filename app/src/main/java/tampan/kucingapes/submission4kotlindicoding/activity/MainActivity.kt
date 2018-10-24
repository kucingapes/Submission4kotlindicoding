/*
 * MainActivity.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 12:37 PM
 * Last modified 10/24/18 10:01 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import org.jetbrains.anko.setContentView
import tampan.kucingapes.submission4kotlindicoding.ankoUI.MainUI
import tampan.kucingapes.submission4kotlindicoding.fragment.FragmentAdapter
import tampan.kucingapes.submission4kotlindicoding.fragment.FragmentFavorite
import tampan.kucingapes.submission4kotlindicoding.fragment.FragmentMatch

class MainActivity : AppCompatActivity() {

    private val past = 0
    private val next = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidNetworking.initialize(this)

        val fragmentAdapter =
            FragmentAdapter(supportFragmentManager)
        val pagePast = FragmentMatch.newInstance(past)
        val pageNext = FragmentMatch.newInstance(next)
        val pageFav = FragmentFavorite()

        fragmentAdapter.addFragment(pagePast)
        fragmentAdapter.addFragment(pageNext)
        fragmentAdapter.addFragment(pageFav)
        MainUI(fragmentAdapter).setContentView(this)
    }
}
