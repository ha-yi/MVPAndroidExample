package com.lombokcyberlab.android.githubjobs.repo.impl

import com.lombokcyberlab.android.githubjobs.presenter.BasePresenter
import com.lombokcyberlab.android.githubjobs.repo.BaseContactor
import com.lombokcyberlab.android.githubjobs.repo.GitJob
import retrofit2.Call

/**
 * Created by ucha on 27/12/17.
 */
class GetSingleJob(val id:String, handler:BasePresenter<GitJob>) : BaseContactor<GitJob>(handler) {
    override fun prepareCaller(): Call<GitJob> {
        return api.getJob(id)
    }
}