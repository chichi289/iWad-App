package com.iwad.app.ui.home.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iwad.app.IWadApp
import com.iwad.app.R
import com.iwad.app.api.model.Post
import com.iwad.app.extentions.ResultCallback
import com.iwad.app.extentions.observeOnce
import com.iwad.app.extentions.toast
import com.iwad.app.ui.base.BaseFragment
import com.iwad.app.ui.home.adapter.PostAdapter
import com.iwad.app.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
    }

    // Post Adapter
    private lateinit var postAdapter:PostAdapter


    override fun layout(): Int = R.layout.fragment_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = context?.applicationContext
        (application as IWadApp).getApplicationComponent()?.inject(this)

        arguments?.let {
            if(it.containsKey("email")){
                context?.toast("Welcome ${it.getString("email")}")
            }
        }
    }

    override fun setObservers() {
        // Post List
        mainActivityViewModel._postListLiveData.observeOnce(this,object :
            ResultCallback<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                postAdapter = PostAdapter(data as ArrayList<Post>){
                        context?.toast(getString(R.string.post_id,it.id.toString()))
                }
                recyclerViewPosts.apply {
                    layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                    adapter = postAdapter
                    setHasFixedSize(true)
                }
            }

            override fun onError(exception: Exception) {
                exception.message?.let {
                    context?.toast(it)
                }
            }
        })
    }

    override fun bindData() {
        mainActivityViewModel.getPosts()
    }
}
