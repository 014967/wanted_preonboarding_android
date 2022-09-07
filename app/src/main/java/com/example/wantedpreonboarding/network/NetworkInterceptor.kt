package com.example.wantedpreonboarding.network

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.net.UnknownHostException

/**
 * @Created by 김현국 2022/09/07
 */
class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            return chain.proceed(
                request = request.newBuilder().apply {
                    header("X-Api-Key", "f515fbe72cbc42469de645a2558e8807")
                }.build()
            )
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException -> {
                }
            }
            return Response.Builder()
                .protocol(Protocol.HTTP_1_1)
                .request(request)
                .message("")
                .code(503)
                .body(ResponseBody.create(null, "$e"))
                .build()
        }
    }
}
