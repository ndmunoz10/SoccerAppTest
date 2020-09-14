package com.nicdamun.repository.helpers

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Failure(val exception: Throwable) : Result<Nothing>()
}