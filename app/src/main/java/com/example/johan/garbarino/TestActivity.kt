package com.example.johan.garbarino


import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.layout_test.*

import com.example.johan.mvvm.*

class TestActivity : AppCompatActivity() {

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_test)
        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.

        val model = ViewModelProviders.of(this).get(MyViewModel::class.java)



        model.getUsers().observe(this, Observer<List<User>>{ users ->
            
            
            txtTexto.text = "usuarios:\n\n"
            if (users != null) {
				for (i in 0..users!!.size){
					var user:User = users[i]
					txtTexto.text = txtTexto.text.toString() +
							"Id: " + user.id.toString() +
							"nombre: " + user.name + "\n\n"
				}
			}
            // update UI
        })
  
    }

}
