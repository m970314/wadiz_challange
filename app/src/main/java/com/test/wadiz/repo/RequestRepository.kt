package com.test.wadiz.repo


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.test.wadiz.data.Body
import com.test.wadiz.data.SearchResponse
import com.test.wadiz.request.ApiRequestFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RequestRepository {
    private var searchinfoMutableLiveData: MutableLiveData<SearchResponse> = MutableLiveData()
    fun requestSearch(keyword: String, completed: (SearchResponse) -> Unit) {
        ApiRequestFactory.retrofit.requestSearch(keyword)
            .enqueue(object : Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    searchinfoMutableLiveData.postValue(response.body())
                    Log.d("1234",response.body()!!.body.list.get(0).title)
                    completed(response.body()!!)
                }
                override fun onFailure(call: Call<SearchResponse?>, t: Throwable) {

                }
            })
    }
    fun getSearchLiveData(): MutableLiveData<SearchResponse> {
        return this.searchinfoMutableLiveData
    }

}