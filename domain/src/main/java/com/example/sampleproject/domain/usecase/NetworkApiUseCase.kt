package com.example.sampleproject.domain.usecase

import com.example.sampleproject.domain.repository.NetworkRepository
import io.reactivex.Observable


class NetworkApiUseCase(private val networkRepository: NetworkRepository) {

    sealed class NetworkResult {
        object Loading : NetworkResult()
        data class Success(val newString: String) : NetworkResult()
        data class Failure(val throwable: Throwable) : NetworkResult()
    }

    fun execute(): Observable<NetworkResult> {
        print("Clicked 2")
        return Observable.just(networkRepository.getApiResponse())
            .map {
                it.also { print(it) }
                NetworkResult.Success(it.toString().toUpperCase())
            }

    }

    fun sampleObservable(): Observable<String> {
        return Observable.just(
            "one",
            "two",
            "three",
            "four",
            "five"
        )
    }
}