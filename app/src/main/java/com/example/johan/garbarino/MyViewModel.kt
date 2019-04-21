package com.example.johan.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel





class User (
    var id:Int? = 0,
    var name:String? = ""
)
//http://www.albertgao.xyz/2018/04/13/why-unresolved-reference-for-viewmodelproviders/






class MyViewModel : ViewModel() {
    private val users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>().also {
            loadUsers()
        }
    }

        init {
            var l: ArrayList<User>? = null
            l?.add(User(1, "johan"))
            l?.add(User(2, "pedro"))
            users.setValue(l)
        }




    fun getUsers(): LiveData<List<User>> {
        return users
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.

//        run {
            var l: ArrayList<User>? = null
            l?.add(User(3, "pepe"))
            l?.add(User(4, "fulano"))
//            users.setValue(l)
//        }


/*
        var l2:ArrayList<User> = ArrayList()

        l2.add(User(3, "joaquin"))
        l2.add(User(4, "alamo"))
        l2.add(User(5, "bauti"))

//        users.setValue(l2)
*/

    }

}

