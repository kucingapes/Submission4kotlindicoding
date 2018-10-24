/*
 * MatchFavoriteAdapter.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 6:33 AM
 * Last modified 10/20/18 3:56 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import tampan.kucingapes.submission4kotlindicoding.`object`.Util
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.startActivity
import tampan.kucingapes.submission4kotlindicoding.activity.DetailActivity
import tampan.kucingapes.submission4kotlindicoding.ankoUI.ItemListUI
import tampan.kucingapes.submission4kotlindicoding.model.MatchFavorite
import java.text.SimpleDateFormat

class MatchFavoriteAdapter(private var data: List<MatchFavorite>) : RecyclerView.Adapter<MatchFavoriteAdapter.Holder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(
            ItemListUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bindItem(context, data[position])
    }

    class Holder(itemView: View) : BaseHolder(itemView) {

        @SuppressLint("SimpleDateFormat")
        fun bindItem(context: Context, match: MatchFavorite) {
            val parseDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val dateFormat = SimpleDateFormat("EEEE',' dd MMMM yyyy")

            val parseDate = parseDateFormat.parse(match.dateEvent)
            val stringDate = dateFormat.format(parseDate)

            eventDate.text = stringDate
            homeTeam.text = match.strHomeTeam
            homeScore.text = match.intHomeScore
            awayTeam.text = match.strAwayTeam
            awayScore.text = match.intAwayScore

            if (homeScore.text == "" && awayScore.text == "") {
                homeScore.text = "-"
                awayScore.text = "-"
            }

            Util.setBadgeImage(match.idHomeTeam, badgeHome)
            Util.setBadgeImage(match.idAwayTeam, badgeAway)

            itemView.setOnClickListener {
                context.startActivity<DetailActivity>("id_event" to match.idEvent,
                    "date_match" to stringDate)
            }
        }

    }
}