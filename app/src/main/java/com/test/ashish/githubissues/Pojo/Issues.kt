package com.test.ashish.githubissues.Pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "issues_table")
class Issues(
    var prNumber: Long,
    var title: String,
    var user: String,
    var patchUrl: String,
    orgName: String,
    repoName: String,
    state: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var orgName: String
    var repoName: String
    var createdAt: Long
    var state: String

    init {
        user = user
        patchUrl = patchUrl
        this.orgName = orgName
        this.repoName = repoName
        this.state = state
        createdAt = Date().time
    }
}