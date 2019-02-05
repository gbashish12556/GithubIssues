package com.test.ashish.githubissues;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.test.ashish.githubissues.Pojo.Issues;

import java.util.List;

public class IssuesViewModel extends AndroidViewModel {

    private IssuesRepository issuesRepository;
    private LiveData<List<Issues>> allIssues;

    public IssuesViewModel(@NonNull Application application) {
        super(application);
        issuesRepository = new IssuesRepository(application);
    }

    public void deleteAll(long time){
        issuesRepository.deleteAllIssues(time);
    }

    public void fetchApi(String orgName, String repoName){
        issuesRepository.fetchApi(orgName, repoName);
    }
    public LiveData<List<Issues>> getAllIssues(String orgname, String repo){
        return issuesRepository.getAllIssues(orgname, repo);
    }

}
