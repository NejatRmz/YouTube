package com.tapmobile.youtube.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(errorMessage: String, data: T? = null) : Resource<T>(data = data, message = errorMessage)
    class Loading<T>(data: T? = null)  : Resource<T>(data = data)
}
