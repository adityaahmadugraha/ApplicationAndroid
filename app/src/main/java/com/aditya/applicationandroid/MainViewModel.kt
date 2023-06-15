package com.aditya.applicationandroid

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {
    private  val _posts = MutableLiveData<List<Posts>>()
    val posts : LiveData<List<Posts>> = _posts

    private  val  _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    init {
        getPosts()
    }

    //fungsi login
    private fun getPosts() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPosts()
        client.enqueue(object : retrofit2.Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    val responseBody = response.body()
                    if (responseBody != null) {
                       _posts.value = responseBody
                    }
                }
            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                _isLoading.value = false
                Log.d("Respons:::::::::",t.message.toString())
            }

        })
    }
}

