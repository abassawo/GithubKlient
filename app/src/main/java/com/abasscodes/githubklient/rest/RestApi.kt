package com.abasscodes.githubklient.rest

import com.abasscodes.githubklient.models.RepoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {
    @GET("/orgs/{org_name}/repos")
    fun searchRepo(@Path("org_name") orgName: String): Single<List<RepoModel>>
}