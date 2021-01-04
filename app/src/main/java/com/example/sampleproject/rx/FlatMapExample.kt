package com.example.sampleproject.rx

import android.annotation.SuppressLint
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit


@SuppressLint("CheckResult")
fun main() {

    sampleObservable()
        .flatMap {
            Observable.just(sampleString()).subscribe(System.out::println)
            Observable.just("$it 1")
        }
        .flatMap {
            Observable.just("$it 2")
        }
        .subscribe(System.out::println)


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

fun sampleString(): String {
    val sleepTime = Random().nextInt(1000) + 500
    Thread.sleep(sleepTime.toLong())
    return "sample"
}
