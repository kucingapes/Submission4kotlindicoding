/*
 * FavoriteDbOpenHelper.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 8:18 AM
 * Last modified 10/23/18 2:57 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.dbHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import tampan.kucingapes.submission4kotlindicoding.`object`.Util

class FavoriteDbOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteMatch.db", null, 1) {

    companion object {
        private var instances: FavoriteDbOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): FavoriteDbOpenHelper {
            if (instances == null) {
                instances =
                        FavoriteDbOpenHelper(context.applicationContext)
            }
            return instances as FavoriteDbOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Util.TABLE_FAVORITE, true,
            Util.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Util.MATCH_ID to TEXT + UNIQUE,
            Util.MATCH_DATE to TEXT,
            Util.HOME_TEAM to TEXT,
            Util.HOME_ID to TEXT,
            Util.HOME_SCORE to TEXT,
            Util.AWAY_TEAM to TEXT,
            Util.AWAY_ID to TEXT,
            Util.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable("TABLE_FAVORITE", true)
    }
}

val Context.database : FavoriteDbOpenHelper
    get() = FavoriteDbOpenHelper.getInstance(applicationContext)