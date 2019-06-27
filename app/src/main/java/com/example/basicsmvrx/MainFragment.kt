package com.example.basicsmvrx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import kotlinx.android.synthetic.main.fragment_main.*

data class GrettingState(
    val title: String = "Meetup #",
    val number: Int = 23
) : MvRxState {
    val titleWithNumber = "$title$number"
}

class GrettingViewModel(initialState: GrettingState) : MvRxViewModel<GrettingState>(initialState) {

    fun incrementNumber() = setState { copy(number = number + 1) }
}

class MainFragment : BaseMvRxFragment() {

    private val viewModel: GrettingViewModel by fragmentViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titleText.setOnClickListener {
            viewModel.incrementNumber()
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        titleText.text = state.titleWithNumber
    }
}