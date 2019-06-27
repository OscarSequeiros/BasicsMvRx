package com.example.basicsmvrx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import kotlinx.android.synthetic.main.fragment_main.*

data class MatchState(val score: Async<Pair<Int, Int>> = Uninitialized) : MvRxState {
    val title = "PER ${score.invoke()?.first ?: "0"} - BRA ${score.invoke()?.second ?: "0"}"
}

class AsyncFragment : BaseMvRxFragment() {

    private val viewModel: MatchViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titleText.setOnClickListener {
            viewModel.fetchScore()
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        titleText.text = when (state.score) {
            is Uninitialized -> "Click to see the score"
            is Loading -> "Loading..."
            is Success -> state.title
            is Fail -> "Fail to retrieve data"
        }
    }
}