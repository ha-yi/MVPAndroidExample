package com.lombokcyberlab.android.githubjobs.repo.impl

import com.lombokcyberlab.android.githubjobs.presenter.BasePresenter
import com.lombokcyberlab.android.githubjobs.repo.BaseContactor
import com.lombokcyberlab.android.githubjobs.repo.GitJob
import retrofit2.Call

/**
 * Created by ucha on 27/12/17.
 */
class GetListJobs(val keyword:String?, val location:String?, val fulltime:Boolean? = null, handler:BasePresenter<MutableList<GitJob>>):BaseContactor<MutableList<GitJob>>(handler) {
    override fun prepareCaller(): Call<MutableList<GitJob>> {
        return api.loadJobs(keyword, location, fulltime)
    }
}