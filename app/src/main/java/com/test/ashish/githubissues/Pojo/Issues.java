package com.test.ashish.githubissues.Pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "issues_table")
public class Issues {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private long prNumber;
    private String title;
    private String user;
    private String orgName;
    private String repoName;
    private String patchUrl;
    private Long createdAt;

    public Issues(long prNumber, String title, String user, String patchUrl, String orgName, String repoName, String state){
        this.prNumber = prNumber;
        this.title = title;
        this.user = user;
        this.patchUrl = patchUrl;
        this.user = user;
        this.patchUrl = patchUrl;
        this.orgName = orgName;
        this.repoName = repoName;
        this.state = state;
        this.createdAt = (new Date()).getTime();
    }

    public long getPrNumber() {
        return prNumber;
    }

    public void setPrNumber(long prNumber) {
        this.prNumber = prNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

}
