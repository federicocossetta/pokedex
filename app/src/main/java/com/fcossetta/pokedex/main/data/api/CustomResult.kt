package com.fcossetta.pokedex.main.data.api

sealed class CustomResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : CustomResult<T>()
    data class Error(val exception: Exception) : CustomResult<Nothing>()
}