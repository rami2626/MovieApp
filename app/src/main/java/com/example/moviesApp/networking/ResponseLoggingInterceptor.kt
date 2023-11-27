package com.example.moviesApp.networking

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException

class ResponseLoggingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val responseString = response.peekBody(Long.MAX_VALUE).string()
        var responseLogTxt = " \nStatus code -> ${response.code}\n"

        responseLogTxt += if (responseString.isNotEmpty()) {
            try {
                val `object` = JSONTokener(responseString).nextValue()
                val jsonLog = if (`object` is JSONObject)
                    `object`.toString(4)
                else
                    (`object` as JSONArray).toString(4)

                "Response body -> \n$jsonLog\n"
            } catch (ex: Exception) {
                "Response body -> \n${ex.message}\n"
            }
        } else {
            "No response\n"
        }

        Log.d(
            "interceptor",
            "$responseLogTxt\n=========================================================================================================\n "
        )

        return response
    }
}