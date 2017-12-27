package com.lombokcyberlab.android.githubjobs.presenter.impl

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.lombokcyberlab.android.githubjobs.R
import com.lombokcyberlab.android.githubjobs.presenter.BasePresenter
import com.lombokcyberlab.android.githubjobs.repo.GitJob
import com.lombokcyberlab.android.githubjobs.repo.impl.GetListJobs
import com.lombokcyberlab.android.githubjobs.view.DetailActivity
import kotlinx.android.synthetic.main.job_item.view.*

/**
 * Created by ucha on 27/12/17.
 */
class JobListingPresenterImpl(
        val recycler: RecyclerView,
        val keyword:String? = null,
        val location:String? = null,
        val fulltime:Boolean? = null,
        context: Context):
        BasePresenter<MutableList<GitJob>>(context) {

    init {
        contactor = GetListJobs(keyword, location, fulltime, this)
        recycler.layoutManager = LinearLayoutManager(context)

    }



    override fun onSuccess(data: MutableList<GitJob>?) {
        interactor?.invoke(data?.size ?: 0)
        recycler.adapter = JobsAdapter(data)
    }

    override fun onError(message: String?, code: Int) {
        interactor?.invoke(0)

    }


    inner class JobsAdapter(val data:MutableList<GitJob>?): RecyclerView.Adapter<JobsAdapter.JobHolder>() {
        override fun onBindViewHolder(holder: JobHolder?, position: Int) {
            holder?.setJob(data?.get(position))
        }

        override fun getItemCount(): Int = data?.size ?: 0

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): JobHolder =
                JobHolder(LayoutInflater.from(context).inflate(R.layout.job_item, parent, false))

        inner class JobHolder(v:View):RecyclerView.ViewHolder(v) {
            fun setJob(job: GitJob?) {
                Glide.with(context).load(job?.company_logo).into(itemView.jobIcon)
                itemView.jobTitle.text = job?.title ?: "- no title -"
                itemView.jobCompany.text = job?.company
                itemView.jobShortDesc.text = Html.fromHtml(job?.description)
                itemView.setOnClickListener {
                    val itt = Intent(context, DetailActivity::class.java)
                    itt.putExtra("id", job?.id)

                    context.startActivity(itt)
                }

            }
        }
    }


}