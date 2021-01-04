package com.example.sampleproject.rx

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import java.util.*
import java.util.concurrent.TimeUnit


@SuppressLint("CheckResult")
fun main() {

    sampleObservable()
        .switchMap {
            Observable.just("$it 1")
        }
        .subscribe(System.out::println)

    switchMap()
}

@SuppressLint("CheckResult")
@Throws(Exception::class)
fun switchMap() {
    val items: List<String> =  listOf(
        "a", "b", "c", "d", "e", "f"
    )
    val scheduler = TestScheduler()
    Observable.fromArray(items)
        .concatMap { s ->
            val delay: Int = Random().nextInt(10)
            Observable.just<String>(s.toString() + "x")
                .delay(delay.toLong(), TimeUnit.SECONDS, scheduler)
        }
        .doOnNext { s -> println(s)  }
        .map { s -> println(s) }
        .subscribe { s -> println(s) }

    scheduler.advanceTimeBy(1, TimeUnit.MINUTES)
}

