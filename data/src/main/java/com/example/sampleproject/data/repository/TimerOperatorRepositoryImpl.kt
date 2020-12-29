package com.example.sampleproject.data.repository

import com.example.sampleproject.domain.repository.TimerOperatorRepository

class TimerOperatorRepositoryImpl : TimerOperatorRepository {

    override fun getUpdatedString(): String {
        return "New String...!!"
    }

}