package com.iwad.app.api.service

import com.iwad.app.api.URLFactory
import com.iwad.app.api.model.Post
import com.iwad.app.api.model.response_parser.AppResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthService {

    @GET(URLFactory.Method.POSTS)
    suspend fun getPostsAsync(): Response<List<Post>>

    @GET("${URLFactory.Method.POST}/{postId}")
    suspend fun getPost(@Path("postId") postId: String): Response<Post>

    @GET("${URLFactory.Method.POST}/{postId}")
    suspend fun getPost2(@Path("postId") postId: String): AppResponse<Post>

}