package com.strukov.core.data

sealed class Result<out R> {
    object Empty : Result<Nothing>()
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}