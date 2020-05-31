package com.iwad.app.api.datasource

import com.iwad.app.api.model.Post
import com.iwad.app.api.model.response_parser.AppResponse
import com.iwad.app.api.model.response_parser.Result
import com.iwad.app.api.repository.AuthRepository
import com.iwad.app.api.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authDataSourceRepository: AuthService
) : BaseDataSource(), AuthRepository {

    override suspend fun getPostAsync(): Result<List<Post>> =
        executeRequest { authDataSourceRepository.getPostsAsync() }

    override suspend fun getPost(postId: String): Result<Post> =
        executeRequest { authDataSourceRepository.getPost(postId) }

    override suspend fun getPost2(postId: String): AppResponse<Post> =
        authDataSourceRepository.getPost2(postId)


}