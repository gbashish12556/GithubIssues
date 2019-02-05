package com.test.ashish.githubissues;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.test.ashish.githubissues.Pojo.Issues;

import java.util.List;

public class IssuesViewModel extends AndroidViewModel {

    private IssuesRepository issuesRepository;
    private LiveData<List<Issues>> allIssues;

    private MutableLiveData<Boolean> status;

    public IssuesViewModel(@NonNull Application application) {
        super(application);
        issuesRepository = new IssuesRepository(application);
    }

    public void deleteAll(long time){
        issuesRepository.deleteAllIssues(time);
    }

    public LiveData<List<Issues>> getAllIssues(String orgname, String repo){

        LiveData<List<Issues>> issues = issuesRepository.getAllIssues(orgname, repo);
        return  issues;
    }

    public MutableLiveData<Boolean> getCurrentStatus() {
        if (status == null) {
            status = issuesRepository.getCurrentStatus();
        }
        return status;
    }

}
