package com.lombokcyberlab.android.githubjobs.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log.e
import com.bumptech.glide.Glide
import com.lombokcyberlab.android.githubjobs.R
import com.lombokcyberlab.android.githubjobs.presenter.impl.JobPresenterImpl
import com.lombokcyberlab.android.githubjobs.repo.GitJob
import com.lombokcyberlab.android.githubjobs.repo.impl.GetSingleJob
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("id")
        e("id", "job id: $id")

        val interactor:(Any)-> Unit = {
            when(it) {is GitJob -> display(it)}
        }

        if (id != null && id.isNotEmpty()) {
            JobPresenterImpl(id, this).withInteractor(interactor).execute()
        }
    }

    private fun display(it: GitJob) {
        titleJob.text = it.title
        company.text = it.company

        Glide.with(this).load(it.company_logo).into(logo)
        descriptions.text = Html.fromHtml(it.description)

    }


}
