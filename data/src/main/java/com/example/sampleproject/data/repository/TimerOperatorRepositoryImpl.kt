package com.example.sampleproject.data.repository

import com.example.sampleproject.domain.repository.TimerOperatorRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TimerOperatorRepositoryImpl : TimerOperatorRepository {

    override fun getUpdatedString(): String {
        return "New String...!!!"
    }

}