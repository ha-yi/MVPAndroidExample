package com.lombokcyberlab.android.githubjobs.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lombokcyberlab.android.githubjobs.R
import com.lombokcyberlab.android.githubjobs.presenter.impl.JobListingPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


// TODO info: Part of Model (V)iew Presenter pattern

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Interactor function object untuk show/hide n
        val interactor: (Any) -> Unit = {
            when(it) {
                is Int -> {
                    nojob.visibility = if (it <= 0) View.VISIBLE else View.GONE
                    recycler.visibility = if (it > 0) View.VISIBLE else View.GONE
                }
            }
        }

        // get all available jobs
        JobListingPresenterImpl(recycler = recycler, context = this).withInteractor(interactor).execute()

        // Cari job
        btnSearch.setOnClickListener {
            hideKeyboard()
            JobListingPresenterImpl(keyword = inputKeyword.text.toString(), recycler = recycler, context = this).withInteractor(interactor).execute()
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}
