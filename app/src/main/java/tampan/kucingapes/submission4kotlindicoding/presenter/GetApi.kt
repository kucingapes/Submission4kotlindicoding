/*
 * GetApikt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 8:04 AM
 * Last modified 10/24/18 8:04 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import tampan.kucingapes.submission4kotlindicoding.BuildConfig
import tampan.kucingapes.submission4kotlindicoding.MainContract
import tampan.kucingapes.submission4kotlindicoding.model.MatchResponse

class GetApi(private var type: Int) : MainContract.GetDataFromApi {

    override fun getMatchList(onSuccessListener: MainContract.GetDataFromApi.OnSuccessListener) {
        when (type) {
            0 -> getFromApi("past", onSuccessListener)
            1 -> getFromApi("next", onSuccessListener)
        }
    }


    private fun getFromApi(typeString: String, onSuccessListener: MainContract.GetDataFromApi.OnSuccessListener) {
        AndroidNetworking.get(BuildConfig.BaseUrl)
            .addPathParameter("event", typeString)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsObject(MatchResponse::class.java, object : ParsedRequestListener<MatchResponse> {
                override fun onResponse(response: MatchResponse) {
                    onSuccessListener.onFinished(response.events)
                }

                override fun onError(anError: ANError) {
                    onSuccessListener.onFailure(anError)
                }

            })
    }
}