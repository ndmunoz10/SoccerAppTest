package com.nicdamun.repository.extensions

import android.accounts.NetworkErrorException
import android.util.Log
import com.nicdamun.repository.helpers.Result
import retrofit2.HttpException
import retrofit2.Response

/**
 * Try a call web service and return a sealed class [Result]
 * when call is success return [Result.Success] or [Result.Failure] if failed
 */
suspend fun <T : Any> safeApiCall(
    call: suspend () -> Result<T>
): Result<T> = try {
    call.invoke()
} catch (e: Exception) {
    Log.e("safeApiCall", e.message.toString())
    Result.Failure(NetworkErrorException(e))
}

/**
 * When [Response] in method isSuccess is true and property body is not null return [Result.Success]
 * or [Result.Failure] in any other condition
 */
fun <T : Any> Response<T>.callServices(): Result<T> {
    if (this.isSuccessful) {
        val body = body()
        return if (body != null) {
            Result.Success(body)
        } else {
            Result.Failure(NullPointerException(this.errorBody().toString()))
        }
    }
    return Result.Failure(HttpException(this))
}