package com.test.ashish.githubissues.Pojo

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

class PullRequest : Serializable {
    @SerializedName("url")
    var url: String? = null

    @SerializedName("html_url")
    var htmlUrl: String? = null

    @SerializedName("diff_url")
    var diffUrl: String? = null

    @SerializedName("patch_url")
    var patchUrl: String? = null

    companion object {
        private const val serialVersionUID = -8668263113909275086L
    }
}