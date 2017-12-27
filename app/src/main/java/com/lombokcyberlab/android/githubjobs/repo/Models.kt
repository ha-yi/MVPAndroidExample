package com.lombokcyberlab.android.githubjobs.repo

/**
 * Created by ucha on 27/12/17.
 */

// TODO info: Part of (M)odel View Presenter pattern

data class GitJob(
        val id: String?,
        val created_at: String?,
        val title: String?,
        val location: String?,
        val type: String?,
        val description: String?,
        val how_to_apply: String?,
        val company: String?,
        val company_url: String?,
        val company_logo: String?,
        val url: String?
)


