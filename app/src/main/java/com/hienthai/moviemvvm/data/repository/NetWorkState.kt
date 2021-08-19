package com.hienthai.moviemvvm.data.repository

class NetWorkState(val status: Status, val msg: String) {

    companion object{
        val LOADDED:NetWorkState
        val LOADING:NetWorkState
        val ERROR:NetWorkState


        init {
            LOADDED = NetWorkState(Status.SUCCESS,"SUCCESS")
            LOADING = NetWorkState(Status.RUNNING,"LOADING")
            ERROR = NetWorkState(Status.FAIL,"ERROR")
        }
    }

}

enum class Status {
    RUNNING, SUCCESS, FAIL
}
