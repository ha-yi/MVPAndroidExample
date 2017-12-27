package com.lombokcyberlab.android.githubjobs.repo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by ucha on 27/12/17.
 */
interface GitJobAPI {
    companion object {
        val BASE_URL = "https://jobs.github.com/"
    }

    @GET("positions.json")
    fun loadJobs(@Query("description") keyword:String?, @Query("location") location:String?, @Query("full_time") fullTime:Boolean?):Call<MutableList<GitJob>>

    @GET("positions/{id}.json")
    fun getJob(@Path("id") id:String):Call<GitJob>
}