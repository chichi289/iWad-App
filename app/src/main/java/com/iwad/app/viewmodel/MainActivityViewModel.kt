@file:Suppress("PropertyName")

package com.iwad.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iwad.app.api.core.AppLiveData
import com.iwad.app.api.model.Post
import com.iwad.app.api.model.response_parser.Result
import com.iwad.app.api.model.response_parser.AppResponse
import com.iwad.app.api.repository.AuthRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val authRepository: AuthRepository) :
    BaseViewModel() {

    // Post List
    private val postListLiveData = MutableLiveData<Result<List<Post>>>()
    val _postListLiveData: LiveData<Result<List<Post>>> = postListLiveData

    // Post
    private val postLiveData = MutableLiveData<Result<Post>>()
    val _postLiveData: LiveData<Result<Post>> = postLiveData

    fun getPosts(){
        withCoroutine{
            val response = authRepository.getPostAsync()
            withContext(Dispatchers.Main){
                postListLiveData.value = response
            }
        }
    }
    fun getPost(postId: String) {
        withCoroutine{
            val response = authRepository.getPost(postId)
            withContext(Dispatchers.Main) {
                postLiveData.value = response
            }
        }
    }

    fun getPost2(postId: String) : AppLiveData<AppResponse<Post>> =
     AppLiveData { authRepository.getPost2(postId) }

}