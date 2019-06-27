package com.example.basicsmvrx

import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import org.koin.android.ext.android.get

class MatchViewModel(initialState: MatchState,
                     private val repository: ScoreRepository): MvRxViewModel<MatchState>(initialState) {

    companion object : MvRxViewModelFactory<MatchViewModel, MatchState> {

        override fun create(viewModelContext: ViewModelContext, state: MatchState): MatchViewModel? {
            val repository : ScoreRepository = viewModelContext.activity.get()
            return MatchViewModel(state, repository)
        }
    }

    fun fetchScore() {
        repository.fetchScore().execute { copy(score = it) }
    }
}