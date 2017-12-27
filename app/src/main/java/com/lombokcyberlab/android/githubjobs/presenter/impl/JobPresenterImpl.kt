package com.lombokcyberlab.android.githubjobs.presenter.impl

import android.content.Context
import com.lombokcyberlab.android.githubjobs.presenter.BasePresenter
import com.lombokcyberlab.android.githubjobs.repo.GitJob
import com.lombokcyberlab.android.githubjobs.repo.impl.GetSingleJob

/**
 * Created by ucha on 27/12/17.
 */
class JobPresenterImpl(val id:String, context: Context) :BasePresenter<GitJob>(context) {
    init {
        contactor = GetSingleJob(id, this)
    }

    override fun onSuccess(data: GitJob?) {
        if(data != null) {
            interactor?.invoke(data)
        }
    }

    override fun onError(message: String?, code: Int) {
        // todo do something when error
    }
}