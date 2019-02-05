package com.test.ashish.githubissues;

import com.test.ashish.githubissues.Pojo.GithubIssuesResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Api {

    @GET("/repos/{orgName}/{repoName}/issues")
    Call<List<GithubIssuesResponse>> getAllResponse(@Path("orgName") String orgname, @Path("repoName") String repoName, @Query("state") String state);

}