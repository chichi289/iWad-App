package com.iwad.app.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.iwad.app.R
import com.iwad.app.api.model.Post
import com.iwad.app.extentions.ResultCallback
import com.iwad.app.extentions.observeOnce
import com.iwad.app.ui.base.BaseActivity
import com.iwad.app.viewmodel.MainActivityViewModel

class MainActivity : BaseActivity() {

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
    }

    override fun createLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        buttonPostList.setOnClickListener {
//            mainActivityViewModel.getPosts()
//        }

//        buttonPost.setOnClickListener {
//
//            mainActivityViewModel.getPost2("1")
//                .setOnError { t->
//                    Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
//                }
//                .liveData().observe(this, Observer {
//                    Toast.makeText(this@MainActivity,"Observed...",Toast.LENGTH_SHORT).show()
//                })
//        }

    }

    override fun setObservers() {
        // Post List
        mainActivityViewModel._postListLiveData.observeOnce(this,object :
            ResultCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                Toast.makeText(this@MainActivity,"${data.size} posts have been fetched",Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: Exception) {
                Toast.makeText(this@MainActivity,exception.message,Toast.LENGTH_SHORT).show()
            }

        })

        // Post
        mainActivityViewModel._postLiveData.observeOnce(this,object : ResultCallback<Post> {

            override fun onSuccess(data: Post) {
                Toast.makeText(this@MainActivity,data.toString(),Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: Exception) {
                Toast.makeText(this@MainActivity,exception.message,Toast.LENGTH_SHORT).show()
            }

        })

        // Post2


    }
}
