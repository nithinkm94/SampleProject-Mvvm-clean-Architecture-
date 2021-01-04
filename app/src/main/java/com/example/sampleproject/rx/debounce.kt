package com.example.sampleproject.rx

import android.annotation.SuppressLint
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

@SuppressLint("CheckResult")
fun main() {
    Observable.just(1, 2, 3, 4, 5, 6)
        .debounce(1, TimeUnit.MICROSECONDS)
        .subscribe { t -> print("Last emitted  = $t" )}
}