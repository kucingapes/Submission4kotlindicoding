/*
 * ApiPresenter.kton Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 7:50 AM
 * Last modified 10/24/18 7:23 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.presenter

import com.androidnetworking.error.ANError
import tampan.kucingapes.submission4kotlindicoding.MainContract
import tampan.kucingapes.submission4kotlindicoding.model.Match

class ApiPresenter(
    private var mainView: MainContract.MainView,
    private var getDataMatch: MainContract.GetDataFromApi) :
    MainContract.Presenter,
    MainContract.GetDataFromApi.OnSuccessListener {

    override fun requestData() {
        getDataMatch.getMatchList(this)
    }

    override fun onFinished(dataList: MutableList<Match>) {
        mainView.setDataList(dataList)
        mainView.progressOff()
    }

    override fun onFailure(anError: ANError) {
        mainView.onFailedResponses(anError)
        mainView.progressOff()
    }
}