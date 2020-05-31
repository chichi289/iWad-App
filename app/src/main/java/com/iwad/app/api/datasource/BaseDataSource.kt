package com.iwad.app.api.datasource

import com.iwad.app.api.model.response_parser.Result
import com.iwad.app.exception.ApiException
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException

open class BaseDataSource {

    suspend fun <T> executeRequest(request: suspend (() -> Response<T>)): Result<T> {
        val result: Result<T>
        result = try {
            val response = request.invoke()

            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else if (response.code() == 401) {
                Result.Unauthorized
            } else {
                Result.Error(Exception(response.errorBody().toString()))
            }

        } catch (e: ConnectException) {
            Result.Error(ConnectException("No Internet"))
        } catch (e: SocketTimeoutException) {
            Result.Error(SocketTimeoutException("Time out"))
        } catch (e: Exception) {
            Result.Error(Exception("Something went wrong"))
        }
        return result
    }

    fun execute(call: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            call()
        }

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()

            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}