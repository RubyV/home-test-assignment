package com.example.home_test_travel.common.vo

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {

    fun isLoading() = (status == Status.LOADING)
    fun isError() = (status == Status.ERROR)
    fun isSuccess() = (status == Status.SUCCESS)

    companion object {
        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }
    }
}

fun <T> T.wrapWithResource(errorMessage: String): Resource<T> {
    return if (this != null) Resource.success(this) else Resource.error(errorMessage)
}
