/*
 * DetailActivity.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 9:57 AM
 * Last modified 10/24/18 8:31 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import tampan.kucingapes.submission4kotlindicoding.BuildConfig
import tampan.kucingapes.submission4kotlindicoding.R
import tampan.kucingapes.submission4kotlindicoding.`object`.Util
import tampan.kucingapes.submission4kotlindicoding.`object`.Util.setBadgeImage
import tampan.kucingapes.submission4kotlindicoding.`object`.Util.setupText
import tampan.kucingapes.submission4kotlindicoding.dbHelper.database
import tampan.kucingapes.submission4kotlindicoding.model.Match
import tampan.kucingapes.submission4kotlindicoding.model.MatchFavorite
import tampan.kucingapes.submission4kotlindicoding.model.MatchResponse

class DetailActivity : AppCompatActivity(), AnkoLogger {
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private var match: Match? = null
    private lateinit var idEvent: String
    private lateinit var dateMatch: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        AndroidNetworking.initialize(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Match Detail"

        idEvent = intent.getStringExtra("id_event")
        dateMatch = intent.getStringExtra("date_match")

        AndroidNetworking.get(BuildConfig.BaseUrlEvent)
            .addQueryParameter("id", idEvent)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(MatchResponse::class.java, object : ParsedRequestListener<MatchResponse> {
                override fun onResponse(response: MatchResponse) {
                    match = response.events[0]
                    setupEvent()
                    loader.visibility = View.GONE
                    container_detail.visibility = View.VISIBLE
                }

                override fun onError(anError: ANError) {
                    error { "my_error" + anError.message }
                    toast("error! \n" + anError.message).show()
                }

            })

        favoriteState()
    }

    private fun setupEvent() {
        setupText(date_match, dateMatch)

        setupText(home_team, match?.strHomeTeam)
        setupText(home_score, match?.intHomeScore)
        setupText(home_goals_player, match?.strHomeGoalDetails)
        setupText(home_shots, match?.intHomeShots)
        setupText(home_yellow, match?.strHomeYellowCards)
        setupText(home_red, match?.strHomeRedCards)
        setupText(home_kiper, match?.strHomeLineupGoalkeeper)
        setupText(home_df, match?.strHomeLineupDefense)
        setupText(home_md, match?.strHomeLineupMidfield)
        setupText(home_fw, match?.strHomeLineupForward)
        setupText(home_sub, match?.strHomeLineupSubstitutes)

        setupText(away_team, match?.strAwayTeam)
        setupText(away_score, match?.intAwayScore)
        setupText(away_goals_player, match?.strAwayGoalDetails)
        setupText(away_shots, match?.intAwayShots)
        setupText(away_yellow, match?.strAwayYellowCards)
        setupText(away_red, match?.strAwayRedCards)
        setupText(away_kiper, match?.strAwayLineupGoalkeeper)
        setupText(away_df, match?.strAwayLineupDefense)
        setupText(away_md, match?.strAwayLineupMidfield)
        setupText(away_fw, match?.strAwayLineupForward)
        setupText(away_sub, match?.strAwayLineupSubstitutes)

        setBadgeImage(match?.idHomeTeam, home_badge)
        setBadgeImage(match?.idAwayTeam, away_badge)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()

            R.id.favorite_action -> {
                if (isFavorite) removeFromFavorite()
                else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Util.TABLE_FAVORITE,
                    Util.ID to match?.idEvent?.toLong(),
                    Util.MATCH_DATE to match?.dateEvent,
                    Util.MATCH_ID to match?.idEvent,
                    Util.HOME_TEAM to match?.strHomeTeam,
                    Util.HOME_ID to match?.idHomeTeam,
                    Util.HOME_SCORE to match?.intHomeScore,
                    Util.AWAY_TEAM to match?.strAwayTeam,
                    Util.AWAY_ID to match?.idAwayTeam,
                    Util.AWAY_SCORE to match?.intAwayScore)
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.toString())
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Util.TABLE_FAVORITE, "(${Util.ID} = {id})",
                    "id" to idEvent)
            }
            toast("Removed from favorite")
        } catch (e: SQLiteConstraintException) {
            error { "my_error ${e.localizedMessage}" }
        }
    }

    private fun setFavorite() {
        if (isFavorite) menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
            R.drawable.ic_star_fill
        )
        else menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this,
            R.drawable.ic_star_border
        )
    }

    private fun favoriteState() {
        database.use {
            val result = select(Util.TABLE_FAVORITE)
                .whereArgs("(${Util.ID}) = {id}",
                    "id" to idEvent)

            val favorite = result.parseList(classParser<MatchFavorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}