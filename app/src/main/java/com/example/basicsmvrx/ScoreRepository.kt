package com.example.basicsmvrx

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ScoreRepository {

    fun fetchScore(): Observable<Pair<Int, Int>> {
        return Observable
            .just(Pair(0, 5))
            .delay(4, TimeUnit.SECONDS)
    }
}