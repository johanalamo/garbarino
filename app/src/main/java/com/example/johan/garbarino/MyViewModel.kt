package com.example.johan.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel





class User (
    var id:Int? = 0,
    var name:String? = ""
)
//http://www.albertgao.xyz/2018/04/13/why-unresolved-reference-for-viewmodelproviders/

//https://medium.com/rocknnull/exploring-kotlin-using-android-architecture-components-and-vice-versa-aa16e600041a




class MyViewModel : ViewModel() {
   private val username = MutableLiveData<String>()

    fun initNetworkRequest() {
        /* expensive operation, e.g. network request */
        username.value = "Peter"
    }

    fun initNetworkRequestDos() {
        /* expensive operation, e.g. network request */
        username.value = "Otro valor posterior"
    }




    fun getUsername(): LiveData<String> {
        return username
    }
}

