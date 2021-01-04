package com.example.sampleproject.presentation.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleproject.data.repository.NetworkRepositoryImpl
import com.example.sampleproject.data.repository.TimerOperatorRepositoryImpl
import com.example.sampleproject.domain.usecase.NetworkApiUseCase
import com.example.sampleproject.domain.usecase.TimerOperatorUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


class MainViewModel() : ViewModel() {

    var testString = MutableLiveData<String>().apply { postValue(" ") }
    var apiResponse = MutableLiveData<String>().apply { postValue("initial value") }
    var valueSum = MutableLiveData<String>().apply { postValue(" ") }
    private var timerOperatorUseCase: TimerOperatorUseCase
    private var networkApiUseCase: NetworkApiUseCase
    private val disposables = CompositeDisposable()

    init {
        testString.value = " "
        val timerOperatorImpl = TimerOperatorRepositoryImpl()
        timerOperatorUseCase = TimerOperatorUseCase(timerOperatorImpl)

        val networkRepositoryImpl = NetworkRepositoryImpl()
        networkApiUseCase = NetworkApiUseCase(networkRepositoryImpl)
        updateText()
    }

    /**
     * Update the first text view
     */
    private fun updateText() {
        timerOperatorUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleResult(it) }
            .addTo(disposables)
    }

    /**
     *  Handles Result
     */
    private fun handleResult(result: TimerOperatorUseCase.Result) {
        when (result) {
            is TimerOperatorUseCase.Result.Success -> {
                testString.value = testString.value + result.newString
            }
            is TimerOperatorUseCase.Result.Failure -> {
                testString.value = "Error"
            }
        }
    }

    /**
     * Method call on button click
     */
    fun callApi() {
        println("Clicked")
        networkApiUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { handleApiListResult(it) }
            .addTo(disposables)
    }

    /**
     * Error Handling
     */
    private fun handleApiListResult(result: NetworkApiUseCase.NetworkResult) {
        /**
         * Show progress here
         * progressVisible.set(result == NetworkResult.Loading)
         */
        when (result) {
            is NetworkApiUseCase.NetworkResult.Success -> {
                apiResponse.value = "Api Resopnse  " + result.newString
            }
            is NetworkApiUseCase.NetworkResult.Failure -> {
                apiResponse.value = "Error"
            }
        }
    }

    /**
     * Get the sum
     */
    fun getResult(first : String, second : String) {
        println("Clicked")
        valueSum.value = timerOperatorUseCase.getResult(first, second)
    }


    fun rxAndroidJust() {
        disposables.add(
            networkApiUseCase.sampleObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<String?>() {
                    override fun onComplete() {
                        Log.d("TAG", "onComplete)")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("TAG", "onError")
                    }

                    override fun onNext(string: String) {
                        Log.d("TAG", "onNext($string)")
                    }
                })
        )
    }

}