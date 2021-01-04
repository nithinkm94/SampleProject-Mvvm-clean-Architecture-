package com.example.sampleproject.rx

import android.annotation.SuppressLint
import io.reactivex.Observable
import java.util.*


@SuppressLint("CheckResult")
fun main() {

    sampleObservable()
        .concatMap {
            val sleepTime = Random().nextInt(1000) + 500

            Thread.sleep(sleepTime.toLong())
            Observable.just("$it 3").subscribe(System.out::println)
            Observable.just("$it 1", sampleString())
        }.concatMap {
            Observable.just("$it 2")
        }
        .subscribe(System.out::println)


}
