package com.lombokcyberlab.android.githubjobs.repo

import android.util.Log.e
import com.lombokcyberlab.android.githubjobs.presenter.BasePresenter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by ucha on 27/12/17.
 */

abstract class BaseContactor<T>(val handler: BasePresenter<T>?) {
    private val interceptor = HttpLoggingInterceptor()
    private val retrofit: Retrofit
    private val client: OkHttpClient
    protected val api: GitJobAPI

    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        client = OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(GitJobAPI.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        api = retrofit.create(GitJobAPI::class.java)
    }

    protected val callback: Callback<T> = object : Callback<T> {
        override fun onFailure(call: Call<T>?, t: Throwable?) {
            e("API::onFailure", "failure on retrofit call: ${t?.message}")
            handler?.onError(t?.message, 99)
            handler?.showProgress(false)
        }

        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            handler?.showProgress(false)
            e("API::onResponse", "Retrofit::onResponse -> code:${response?.code()} body:${response?.isSuccessful}")

            if (response?.isSuccessful ?: false) {
                handler?.onSuccess(response?.body())
            } else {
                handler?.onError("code:${response?.code()} errorbody:${response?.errorBody()?.string()}", response?.code() ?: 90)
            }
        }
    }


    abstract protected fun prepareCaller(): Call<T>

    fun execute() {
        e("contactor","EXECUTE::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
        handler?.showProgress(true)
        val call = prepareCaller()
        call.enqueue(callback)
    }
}