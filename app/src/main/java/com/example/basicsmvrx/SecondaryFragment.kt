package com.example.basicsmvrx

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MvRxState
import kotlinx.android.synthetic.main.fragment_main.*

data class HelloState(
    val title: String = "Meetup #",
    val number: Int = 23
) : MvRxState {
    val titleWithNumber = "$title$number"
}

class SecondaryFragment : Fragment() {

    private val state = HelloState()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titleText.text = state.titleWithNumber
    }
}