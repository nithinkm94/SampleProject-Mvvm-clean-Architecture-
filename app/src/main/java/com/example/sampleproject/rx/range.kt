package com.example.sampleproject.rx

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


@SuppressLint("CheckResult")
fun main() {
    Observable.range(1, 20)
        .subscribe { t -> print("$t ") }

    println("-")

    Observable.fromArray(1, 20)
        .subscribe { t -> println("$t ") }

    println("-")

    Observable.just(listOf("Apple", "Orange", "Banana"))
        .subscribe(
            { value -> println("Received: $value") },      // onNext
            { error -> println("Error: $error") },         // onError
            { println("Completed") }                       // onComplete
        )

    println("-")

    Observable.fromArray(listOf("Apple", "Orange", "Banana"))
        .subscribe(
            { value -> println("Received: $value") },      // onNext
            { error -> println("Error: $error") },         // onError
            { println("Completed") }                       // onComplete
        )

    Observable.just(1, 20)
        .subscribe { t -> println("$t ") }


    println("Filter ")
    val filterObservable: Observable<String> =
        Observable.fromArray("Hello", "How are you?", "doing")
    filterObservable.filter { string: String ->
        string.contains(
            " "
        )
    }.subscribe { s: String? -> println(s) }

    println("Filter ")

}