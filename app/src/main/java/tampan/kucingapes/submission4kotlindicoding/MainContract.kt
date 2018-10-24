/*
 * MainContract.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 6:38 AM
 * Last modified 10/24/18 6:38 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission4kotlindicoding.model.Match
import tampan.kucingapes.submission4kotlindicoding.model.MatchFavorite

interface MainContract {
    interface Presenter {
        fun requestData()
    }

    interface MainView {
        fun progressOn()
        fun progressOff()
        fun setDataList(dataList: MutableList<Match>)
        fun onFailedResponses(anError: ANError)
    }

    interface GetDataFromApi {
        interface OnSuccessListener {
            fun onFinished(dataList: MutableList<Match>)
            fun onFailure(anError: ANError)
        }

        fun getMatchList(onSuccessListener: OnSuccessListener)
    }

    interface FavoriteView {
        fun progressOn()
        fun progressOff()
        fun setDataList(dataList: List<MatchFavorite>)
    }

    interface GetDataFromSQL {
        interface OnSuccessListenerSQL {
            fun onFinished(dataList: List<MatchFavorite>)
            fun onFailure(anError: ANError)
        }

        fun getMatchListSQL(onSuccessListenerSQL: OnSuccessListenerSQL)
    }
}