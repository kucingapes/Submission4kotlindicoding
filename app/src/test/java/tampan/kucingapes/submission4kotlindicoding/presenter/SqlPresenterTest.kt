/*
 * SqlPresenterTest.kt on Submission4kotlindicoding
 * Developed by Muhammad Utsman on 10/24/18 9:59 AM
 * Last modified 10/24/18 9:59 AM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission4kotlindicoding.presenter

import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import tampan.kucingapes.submission4kotlindicoding.MainContract
import tampan.kucingapes.submission4kotlindicoding.model.MatchFavorite

class SqlPresenterTest {

    @Mock
    private lateinit var view: MainContract.FavoriteView

    @Mock
    private lateinit var presenter: SqlPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SqlPresenter(view, GetSql(context = null))
    }

    @Test
    fun requestData() {
        val dataList: List<MatchFavorite> = ArrayList()

        presenter.requestData()
        presenter.onFinished(dataList)

        verify(view).setDataList(dataList)
    }
}