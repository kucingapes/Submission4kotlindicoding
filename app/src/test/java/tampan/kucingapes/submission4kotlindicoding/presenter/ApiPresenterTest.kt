/*
 * ApiPresenterTest.kton Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 7:50 AM
 * Last modified 10/24/18 7:50 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.presenter

import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import tampan.kucingapes.submission4kotlindicoding.MainContract
import tampan.kucingapes.submission4kotlindicoding.model.Match

class ApiPresenterTest {

    @Mock
    private lateinit var mainView: MainContract.MainView

    @Mock
    private lateinit var presenter: ApiPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = ApiPresenter(mainView, GetApi(1))
    }

    @Test
    fun requestDataTest() {
        val dataList: MutableList<Match> = mutableListOf()

        presenter.requestData()
        presenter.onFinished(dataList)

        verify(mainView).setDataList(dataList)
    }
}