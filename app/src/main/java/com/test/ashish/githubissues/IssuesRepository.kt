package com.test.ashish.githubissues

import com.test.ashish.githubissues.IssuesDatabase.Companion.getInstance
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.test.ashish.githubissues.Pojo.Issues
import androidx.lifecycle.LiveData
import com.test.ashish.githubissues.Pojo.GithubIssuesResponse
import android.os.AsyncTask
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class IssuesRepository(application: Application?) {
    private val issuesDao: IssuesDao?
    private var status: MutableLiveData<Boolean>? = null
    fun insert(issues: List<Issues?>?) {
        InsertAsyncTask(issuesDao).execute(issues)
    }

    //
    fun deleteAllIssues(time: Long) {
        DeleteAllAsyncTask(issuesDao).execute(time)
    }

    fun getAllIssues(orgName: String, repoName: String): LiveData<List<Issues?>?>? {
        val issues = issuesDao!!.getAllIssue(orgName, repoName)
        if (issues!!.value == null) {
            fetchApi(orgName, repoName)
        }
        return issues
    }

    val currentStatus: MutableLiveData<Boolean>
        get() {
            if (status == null) {
                status = MutableLiveData()
            }
            return status!!
        }

    fun fetchApi(orgName: String, repoName: String) {
        fetchData(orgName, repoName)
    }

    fun fetchData(orgName: String, repoName: String) {
        Log.d("responseBody", "$orgName : $repoName")
        val call1 = RetrofitClient.getInstance().api.getAllResponse(orgName, repoName, "all")
        call1!!.enqueue(object : Callback<List<GithubIssuesResponse?>?>{
            override fun onResponse(
                call: Call<List<GithubIssuesResponse?>?>?,
                response: Response<List<GithubIssuesResponse?>?>?
            ) {
                Log.d("responseBody", response?.body().toString())
                if (response?.code() == 200) {
                    val reponse = response.body()
                    if (reponse?.size!! > 0) {
                        insert(convertToIssues(reponse as List<GithubIssuesResponse>))
                    } else {
                        status!!.setValue(false)
                    }
                } else {
                    status!!.setValue(false)
                }
            }

            override fun onFailure(call: Call<List<GithubIssuesResponse?>?>?, t: Throwable?) {
                status!!.setValue(false)
            }

        })


        val call2 = RetrofitClient.getInstance().api.getAllResponse(orgName, repoName, "closed")
        call2!!.enqueue(object : Callback<List<GithubIssuesResponse?>?>{
            override fun onResponse(
                call: Call<List<GithubIssuesResponse?>?>?,
                response: Response<List<GithubIssuesResponse?>?>?
            ) {
                if (response!!.code() == 200) {
                    val reponse = response.body()
                    insert(convertToIssues(reponse as List<GithubIssuesResponse>))
                    if (reponse.size > 0) {
                        insert(convertToIssues(reponse))
                    } else {
                        status!!.setValue(false)
                    }
                } else {
                    status!!.setValue(false)
                }
            }

            override fun onFailure(call: Call<List<GithubIssuesResponse?>?>?, t: Throwable?) {
                status!!.setValue(false)
            }

        })
    }

    fun convertToIssues(response: List<GithubIssuesResponse>): List<Issues?> {
        val issues = ArrayList<Issues?>()
        for (i in response.indices) {
            val item = response[i]
            val repoUrlArray =
                item.repositoryUrl!!.replace("https://api.github.com/repos", "").split("/")
                    .toTypedArray()
            var pathcUrl = ""
            if (item.pullRequest != null) {
                pathcUrl = item.pullRequest!!.patchUrl.toString()
            }
            issues.add(
                Issues(
                    item.number!!, item.title!!, item.user!!.login!!,
                    pathcUrl, repoUrlArray[1], repoUrlArray[2], item.state!!
                )
            )
        }
        return issues
    }

    class InsertAsyncTask(private val issuesDao: IssuesDao?) :
        AsyncTask<List<Issues?>?, Void?, Void?>() {
        override fun doInBackground(vararg params: List<Issues?>?): Void? {
            issuesDao!!.insert(params[0])
            return null
        }
    }

    class DeleteAllAsyncTask(private val issuesDao: IssuesDao?) : AsyncTask<Long?, Void?, Void?>() {
        override fun doInBackground(vararg params: Long?): Void? {
            params[0]?.let { issuesDao!!.deleteAllNotes(it) }
            return null
        }

    }

    init {
        val noteDatabase = getInstance(application!!)
        issuesDao = noteDatabase!!.issuesDao()
    }
}