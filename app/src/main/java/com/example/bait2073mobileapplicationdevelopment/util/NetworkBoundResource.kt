package com.example.bait2073mobileapplicationdevelopment.util

import kotlinx.coroutines.flow.*

//This pattern helps manage data synchronization between a local database
// and a remote server while providing clear feedback to the user
// interface about the state of the data retrieval process.

inline fun <ResultType : Any, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    // If shouldFetch returns true, it proceeds to fetch fresh data.
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            //saveFetchResult: A suspending function that saves the fetched
            // data to the local database.
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}