/*
 * BaseHolder.kt on Submission3KADEdicoding
 * Developed by Muhammad Utsman on 10/20/18 2:30 PM
 * Last modified 10/20/18 3:33 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.find
import tampan.kucingapes.submission4kotlindicoding.R

open class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val eventDate: TextView = itemView.find(R.id.date_match)
    val homeTeam: TextView = itemView.find(R.id.home_team)
    val homeScore: TextView = itemView.find(R.id.home_score)
    val awayTeam: TextView = itemView.find(R.id.away_team)
    val awayScore: TextView = itemView.find(R.id.away_score)
    val badgeHome: ImageView = itemView.find(R.id.badge_home)
    val badgeAway: ImageView = itemView.find(R.id.badge_away)
}