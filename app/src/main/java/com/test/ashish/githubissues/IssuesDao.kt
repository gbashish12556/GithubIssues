package com.test.ashish.githubissues


import com.test.ashish.githubissues.Pojo.Issues
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IssuesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(issues: List<Issues?>?)

    @Query("DELETE FROM issues_table WHERE createdAt <:time")
    fun deleteAllNotes(time: Long)

    @Query("SELECT*FROM issues_table WHERE orgName = :orgName AND repoName=:repoName")
    fun getAllIssue(orgName: String?, repoName: String?): LiveData<List<Issues?>?>?
}