package ru.lingstra.avitocopy.ui.filters

import ru.lingstra.avitocopy.R
import ru.lingstra.avitocopy.presentation.base.StubPresenter
import ru.lingstra.avitocopy.presentation.base.StubView
import ru.lingstra.avitocopy.ui.base.MviBaseFragment

class FiltersFragment: MviBaseFragment<StubView, StubPresenter>() {
    override fun createPresenter(): StubPresenter = StubPresenter()

    override val layoutRes: Int
        get() = R.layout.fragment_filters
}