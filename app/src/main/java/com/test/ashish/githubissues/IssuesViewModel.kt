package com.test.ashish.githubissues

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.ashish.githubissues.Pojo.Issues

class IssuesViewModel(application: Application) : AndroidViewModel(application) {
    private val issuesRepository: IssuesRepository
    private val allIssues: LiveData<List<Issues>>? = null
    private var status: MutableLiveData<Boolean>? = null
    fun deleteAll(time: Long) {
        issuesRepository.deleteAllIssues(time)
    }

    fun getAllIssues(
        orgname: String?,
        repo: String?
    ): LiveData<List<Issues?>?>? {
        return issuesRepository.getAllIssues(orgname!!, repo!!)
    }

    val currentStatus: MutableLiveData<Boolean>?
        get() {
            if (status == null) {
                status = issuesRepository.currentStatus
            }
            return status
        }

    init {
        issuesRepository = IssuesRepository(application)
    }
}