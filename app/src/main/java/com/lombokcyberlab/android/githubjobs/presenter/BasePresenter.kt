package com.lombokcyberlab.android.githubjobs.presenter

import android.app.ProgressDialog
import android.content.Context
import com.lombokcyberlab.android.githubjobs.repo.BaseContactor

/**
 * Created by ucha on 27/12/17.
 */

// TODO info: Part of Model View (P)resenter pattern

abstract class BasePresenter<T>(val context: Context) {
    var contactor: BaseContactor<T>? = null
    abstract fun onSuccess(data:T?)
    abstract fun onError(message:String?, code:Int)
    private val dlg= ProgressDialog(context)

    open fun showProgress(show:Boolean = true) {
        if (show) {
            dlg.show()
        } else {
            dlg.dismiss()
        }
    }
    protected var interactor:((Any) -> Unit)? = null

    fun withInteractor(fn: (Any) -> Unit):BasePresenter<T> {
        this.interactor = fn
        return this
    }

    fun execute() {
        contactor?.execute()
    }
}