package com.example.sampleproject.domain.usecase

import com.example.sampleproject.domain.repository.TimerOperatorRepository
import com.example.sampleproject.domain.util.TestUtil
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable


class TimerOperatorUseCase(private val timerOperatorRepository: TimerOperatorRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val newString: String) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    fun execute(): Observable<Result> {
        return timerOperatorRepository.getUpdatedString()
            .map {
                it.also { println(it) }
                Result.Success(it.toString().toUpperCase())
            }.toObservable()
    }

    fun getResult(first : String, second : String) : String {
        return if(TestUtil.validate(first, second)){
            val sum = first.toInt() + second.toInt()
            "Sum is  $sum"
        }else{
            "Error Input"
        }
    }
}