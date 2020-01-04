package com.geniusforapp.mashkor.resourcses

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @name mashkor
 * Copyrights (c) 2020-01-04 Created By Ahmad Najar
 **/
fun <T> Single<T>.withThreading(): Single<T> = compose {
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.withThreading(): Observable<T> = compose {
    subscribeOn(Schedulers.io())
    observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.withThreading(): Flowable<T> = compose {
    subscribeOn(Schedulers.io())
    observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.withThreading(): Maybe<T> = compose {
    subscribeOn(Schedulers.io())
    observeOn(AndroidSchedulers.mainThread())
}