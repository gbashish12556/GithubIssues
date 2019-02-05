package com.test.ashish.githubissues;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.test.ashish.githubissues.Pojo.Issues;

import java.util.List;

@Dao
public interface IssuesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Issues> issues);

    @Query("DELETE FROM issues_table WHERE createdAt <:time")
    void deleteAllNotes(long time);

    @Query("SELECT*FROM issues_table WHERE orgName = :orgName AND repoName=:repoName")
    LiveData<List<Issues>> getAllIssue(String orgName, String repoName);

}
