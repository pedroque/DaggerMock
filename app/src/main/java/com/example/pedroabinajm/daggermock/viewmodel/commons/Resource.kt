package com.example.pedroabinajm.daggermock.viewmodel.commons

open class Resource<out T>(
        val status: Status,
        val data: T? = null,
        val error: Throwable? = null
) {
    open val isEmpty: Boolean
        get() = data == null || (data is Collection<*> && data.isEmpty())

    companion object {
        fun <T> loading(data: T?) = Resource(Status.LOADING, data)
        fun <T> success(data: T?) = Resource(Status.SUCCESS, data)
        fun <T> completed(data: T?) = Resource(Status.COMPLETED, data)
        fun <T> error(error: Throwable?, data: T? = null) = Resource(Status.ERROR, data, error)
    }

    enum class Status {
        SUCCESS, ERROR, LOADING, COMPLETED
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Resource<*>

        if (status != other.status) return false
        if (data != other.data) return false
        if (error != other.error) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        result = 31 * result + (error?.hashCode() ?: 0)
        return result
    }
}