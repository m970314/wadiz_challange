package com.test.wadiz.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.wadiz.data.Body
import com.test.wadiz.data.SearchResponse
import com.test.wadiz.repo.RequestRepository

class SearchViewModel(application: Application) : AndroidViewModel(application){
    var searchinfoMutableLiveData: MutableLiveData<SearchResponse> = MutableLiveData()
    private var SearchRepository: RequestRepository
    var progressVisible = MutableLiveData<Boolean>()
    init {
        SearchRepository = RequestRepository()
        searchinfoMutableLiveData = SearchRepository.getSearchLiveData()
    }
    fun search(keyword : String) {
        progressVisible.postValue(true)
        SearchRepository.requestSearch(keyword){
            searchinfoMutableLiveData.postValue(it)
            progressVisible.postValue(false)
        }
    }
}

