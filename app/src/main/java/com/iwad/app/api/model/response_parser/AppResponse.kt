package com.iwad.app.api.model.response_parser

import com.google.gson.annotations.SerializedName

data class AppResponse<T>(
    @field:SerializedName("code")
    val code: Int = 0,
    @field:SerializedName("message")
    val message: String? = null,
    @field:SerializedName("data")
    val data: T? = null)