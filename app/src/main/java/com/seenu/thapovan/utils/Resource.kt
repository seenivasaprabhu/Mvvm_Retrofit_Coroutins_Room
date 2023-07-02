package com.seenu.thapovan.utils

//data class for handle generic response 
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
	//enum class for response status
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
	//function for handle success response
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }
	//function for handle failure response
        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }
	//function for handle loading
        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
