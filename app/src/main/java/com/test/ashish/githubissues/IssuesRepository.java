package com.test.ashish.githubissues;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.test.ashish.githubissues.Pojo.GithubIssuesResponse;
import com.test.ashish.githubissues.Pojo.Issues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssuesRepository {

    private IssuesDao issuesDao;

    public IssuesRepository(Application application){
        IssuesDatabase  noteDatabase = IssuesDatabase.getInstance(application);
        issuesDao = noteDatabase.issuesDao();
    }

    public void insert(List<Issues> issues){
        new InsertAsyncTask(issuesDao).execute(issues);
    }
//
    public void deleteAllIssues(long time){
        new DeleteAllAsyncTask(issuesDao).execute(time);
    }

    public LiveData<List<Issues>> getAllIssues(String orgName, String repoName) {
        LiveData<List<Issues>> issues = issuesDao.getAllIssue(orgName, repoName);
        return issues;
    }

    public void fetchApi(String orgName, String repoName){

            fetchData(orgName, repoName);
    }

    public void fetchData(String orgName, String repoName){

        retrofit2.Call<List<GithubIssuesResponse>> call1 = RetrofitClient.getInstance().getApi().getAllResponse(orgName,repoName,"open");

        call1.enqueue(new Callback<List<GithubIssuesResponse>>() {

            @Override
            public void onResponse(Call<List<GithubIssuesResponse>> call, Response<List<GithubIssuesResponse>> response) {

                if (response.code() == 200) {
                    List<GithubIssuesResponse> reponse = response.body();
                    insert(convertToIssues(reponse));
                }
            }

            @Override
            public void onFailure(Call<List<GithubIssuesResponse>> call, Throwable t) {
            }

        });


        retrofit2.Call<List<GithubIssuesResponse>> call2 = RetrofitClient.getInstance().getApi().getAllResponse(orgName,repoName,"closed");


        call2.enqueue(new Callback<List<GithubIssuesResponse>>() {

            @Override
            public void onResponse(Call<List<GithubIssuesResponse>> call, Response<List<GithubIssuesResponse>> response) {
                if (response.code() == 200) {
                    List<GithubIssuesResponse> reponse = response.body();
                    insert(convertToIssues(reponse));
                }
            }

            @Override
            public void onFailure(Call<List<GithubIssuesResponse>> call, Throwable t) {

            }

        });


    }

    public List<Issues> convertToIssues(List<GithubIssuesResponse> response){
        ArrayList<Issues> issues = new ArrayList<>();
        for(int i=0;i<response.size();i++){

            GithubIssuesResponse item = response.get(i);

             Log.d("repoUrl",item.getRepositoryUrl());
             String[] repoUrlArray = item.getRepositoryUrl().replace("https://api.github.com/repos","").split("/");

            String pathcUrl = "";
            if(item.getPullRequest() != null){
                 pathcUrl = String.valueOf(item.getPullRequest().getPatchUrl());
            }

            issues.add(new Issues(item.getNumber(), item.getTitle(), item.getUser().getLogin(),
                    pathcUrl,repoUrlArray[1], repoUrlArray[2], item.getState()));
        }
        return issues;
    }

    public static class InsertAsyncTask extends AsyncTask<List<Issues>, Void,Void> {

        private IssuesDao issuesDao;

        public InsertAsyncTask(IssuesDao issuesDao){
            this.issuesDao = issuesDao;
        }

        @Override
        protected Void doInBackground(List<Issues>... notes) {
            issuesDao.insert(notes[0]);
            return null;
        }
    }

    public static class DeleteAllAsyncTask extends AsyncTask<Long, Void,Void>{

        private IssuesDao issuesDao;

        public DeleteAllAsyncTask(IssuesDao issuesDao){
            this.issuesDao = issuesDao;

        }

        @Override
        protected Void doInBackground(Long... now) {
            issuesDao.deleteAllNotes(now[0]);
            return null;
        }
    }

}
