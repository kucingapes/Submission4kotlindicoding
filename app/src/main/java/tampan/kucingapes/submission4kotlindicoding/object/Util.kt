/*
 * Util.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 6:34 AM
 * Last modified 10/24/18 1:08 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.`object`

import android.widget.ImageView
import android.widget.TextView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.squareup.picasso.Picasso
import tampan.kucingapes.submission4kotlindicoding.BuildConfig
import tampan.kucingapes.submission4kotlindicoding.model.TeamsResponse

object Util {

    const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
    const val ID: String = "ID_"
    const val MATCH_DATE: String = "MATCH_DATE"
    const val MATCH_ID = "MATCH_ID"
    const val HOME_TEAM = "HOME_TEAM"
    const val HOME_ID = "HOME_ID"
    const val HOME_SCORE = "HOME_SCORE"
    const val AWAY_TEAM = "AWAY_TEAM"
    const val AWAY_ID = "AWAY_ID"
    const val AWAY_SCORE = "AWAY_SCORE"

    fun setBadgeImage(idTeam: String?, badge: ImageView) {
        AndroidNetworking.get(BuildConfig.BaseUrlTeam)
            .addQueryParameter("id", idTeam)
            .build()
            .getAsObject(TeamsResponse::class.java, object : ParsedRequestListener<TeamsResponse> {
                override fun onResponse(response: TeamsResponse) {
                    val urlBadge = response.teams[0].strTeamBadge
                    Picasso.get()
                        .load(urlBadge)
                        .fit()
                        .centerCrop()
                        .into(badge)
                }

                override fun onError(anError: ANError?){}

            })
    }

    fun setupText(view: TextView, data: String?) {
        view.apply {
            text = data
            if (text == "") {
                text = "-"
            }
        }
    }
}