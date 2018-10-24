/*
 * GetSql.ktubmission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 9:47 AM
 * Last modified 10/24/18 9:47 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.presenter

import android.content.Context
import android.view.View
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import tampan.kucingapes.submission4kotlindicoding.MainContract
import tampan.kucingapes.submission4kotlindicoding.`object`.Util
import tampan.kucingapes.submission4kotlindicoding.dbHelper.database
import tampan.kucingapes.submission4kotlindicoding.model.MatchFavorite

class GetSql(private var context: Context?) : MainContract.GetDataFromSQL {

    override fun getMatchListSQL(onSuccessListenerSQL: MainContract.GetDataFromSQL.OnSuccessListenerSQL) {
        context?.database?.use {
            val result = select(Util.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<MatchFavorite>())
            onSuccessListenerSQL.onFinished(favorite)

        }
    }

}