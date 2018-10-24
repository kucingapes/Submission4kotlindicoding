/*
 * SqlPresentern Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 8:46 AM
 * Last modified 10/24/18 8:46 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.presenter

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission4kotlindicoding.MainContract
import tampan.kucingapes.submission4kotlindicoding.model.MatchFavorite

class SqlPresenter(private var favoriteView: MainContract.FavoriteView,
                   private var getDataMatch: MainContract.GetDataFromSQL) :
    MainContract.Presenter, MainContract.GetDataFromSQL.OnSuccessListenerSQL {

    override fun requestData() {
        getDataMatch.getMatchListSQL(this)
    }

    override fun onFinished(dataList: List<MatchFavorite>) {
        favoriteView.setDataList(dataList)
        favoriteView.progressOff()
    }

    override fun onFailure(anError: ANError) {
        favoriteView.progressOff()
    }

}