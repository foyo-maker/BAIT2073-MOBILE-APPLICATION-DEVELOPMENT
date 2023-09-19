package com.example.bait2073mobileapplicationdevelopment.util

// sealed class to represent the various states of loading data
// from a remote API or database and handle success, loading,
// and error cases in your application.
sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}