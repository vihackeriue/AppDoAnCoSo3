package com.example.appdoancoso3.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DetailWorkModel (

    var ID: Int,
    var title: String?,
    var date_created: String?,
    var time_hours: String?,
    var time_minute: String?,
    var content: String?,
    var status: Int?,
    var IDWorks: Int?
        )


class WorkDetailViewModel : ViewModel() {
    private val _workLiveData = MutableLiveData<List<DetailWorkModel>>()

    fun setMyLiveData(list: List<DetailWorkModel>) {
        _workLiveData.value = list
    }

    fun getMyLiveData(): LiveData<List<DetailWorkModel>> {
        return _workLiveData
    }
}