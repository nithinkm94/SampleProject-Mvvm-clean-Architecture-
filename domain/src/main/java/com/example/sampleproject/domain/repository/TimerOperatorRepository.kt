package com.example.sampleproject.domain.repository

import io.reactivex.Observable
import io.reactivex.Single

interface TimerOperatorRepository {
    fun getUpdatedString(): String
}