package com.example.basicsmvrx

import org.koin.dsl.module

val matchModule = module {
    factory { ScoreRepository() }
}