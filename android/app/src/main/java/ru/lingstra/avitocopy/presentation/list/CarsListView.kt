package ru.lingstra.avitocopy.presentation.list

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable
import ru.lingstra.avitocopy.domain.list.CarsListViewState

interface CarsListView: MvpView {
    fun loadMore(): Observable<Int>
    fun goToOriginal(): Observable<String>

    fun render(state: CarsListViewState)
}