package com.example.sampleproject.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleproject.data.repository.TimerOperatorRepositoryImpl
import com.example.sampleproject.domain.repository.TimerOperatorRepository
import com.example.sampleproject.domain.usecase.TimerOperatorUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class MainViewModel() : ViewModel() {

    var testString = MutableLiveData<String>().apply { postValue("initial value") }
    lateinit var timerOperatorUseCase: TimerOperatorUseCase
    val disposables = CompositeDisposable()

    init {
        Log.d("Tag", "Init")
        testString.value = "Initialized"
        val timerOperatorImpl = TimerOperatorRepositoryImpl()
        timerOperatorUseCase = TimerOperatorUseCase(timerOperatorImpl)
        updateText()
    }

    private fun updateText() {
        timerOperatorUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleGetRestaurantListResult(it) }
            .addTo(disposables)
    }

    // Handles Result from getRestaurantListUseCase
    private fun handleGetRestaurantListResult(result: TimerOperatorUseCase.Result) {
//        progressVisible.set(result == Result.Loading)
        when (result) {
            is TimerOperatorUseCase.Result.Success -> {
                testString.value = "New Value  " + result.newString
            }
            is TimerOperatorUseCase.Result.Failure -> {
                testString.value = "Error"
            }
        }
    }


}