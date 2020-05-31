package com.iwad.app.api.repository

import com.iwad.app.api.model.Post
import com.iwad.app.api.model.response_parser.AppResponse
import com.iwad.app.api.model.response_parser.Result

interface AuthRepository {

    suspend fun getPostAsync(): Result<List<Post>>

    suspend fun getPost(postId: String): Result<Post>

    suspend fun getPost2(postId: String): AppResponse<Post>

}