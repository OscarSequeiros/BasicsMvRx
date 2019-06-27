package com.example.basicsmvrx

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_main.*

@Parcelize
data class Value(
    val number: Int,
    val factor: Int
) : Parcelable

data class GreetingState(
    val title: String = "Meetup #",
    @PersistState val value: Value = Value(23, 3)
) : MvRxState {
    val titleWithNumber = "$title${value.number}"
}

class GrettingViewModel(initialState: GreetingState) : MvRxViewModel<GreetingState>(initialState) {

    fun incrementNumber() = setState {
        Log.e("TAG-Thread", Thread.currentThread().toString())
        copy(value = value.copy(number = value.number + 1)) }
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