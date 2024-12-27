package id.aflah.universitieslist.domain

sealed class ResultState<out R> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val exception: Exception) : ResultState<Nothing>()
    data object Loading : ResultState<Nothing>()
}