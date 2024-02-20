package com.example.universitieslist.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherHelper {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}
