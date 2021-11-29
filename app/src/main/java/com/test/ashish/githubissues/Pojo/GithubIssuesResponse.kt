package com.test.ashish.githubissues.Pojo

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.test.ashish.githubissues.Pojo.PullRequest
import java.io.Serializable

class GithubIssuesResponse : Serializable {
    @SerializedName("url")
    var url: String? = null

    @SerializedName("repository_url")
    var repositoryUrl: String? = null

    @SerializedName("labels_url")
    var labelsUrl: String? = null

    @SerializedName("comments_url")
    var commentsUrl: String? = null

    @SerializedName("events_url")
    var eventsUrl: String? = null

    @SerializedName("html_url")
    var htmlUrl: String? = null

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("node_id")
    var nodeId: String? = null

    @SerializedName("number")
    var number: Long? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("user")
    var user: User? = null

    @SerializedName("labels")
    var labels: List<Label>? = null

    @SerializedName("state")
    var state: String? = null

    @SerializedName("locked")
    var locked: Boolean? = null

    @SerializedName("assignee")
    var assignee: Any? = null

    @SerializedName("assignees")
    var assignees: List<Any>? = null

    @SerializedName("milestone")
    var milestone: Any? = null

    @SerializedName("comments")
    var comments: Long? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("closed_at")
    var closedAt: Any? = null

    @SerializedName("author_association")
    var authorAssociation: String? = null

    @SerializedName("pull_request")
    var pullRequest: PullRequest? = null

    @SerializedName("body")
    var body: String? = null

    companion object {
        private const val serialVersionUID = 8920915776603054L
    }
}