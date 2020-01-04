package com.geniusforapp.mashkor.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @name mashkor
 * Copyrights (c) 2020-01-03 Created By Ahmad Najar
 **/


private val okHttpClient = OkHttpClient.Builder()


val httpLoggingInterceptor =
    HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

private val okHttpClientWithLogger = okHttpClient
    .addInterceptor(httpLoggingInterceptor)


val client = okHttpClientWithLogger.build()