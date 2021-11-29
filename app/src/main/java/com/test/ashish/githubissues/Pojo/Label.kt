package com.test.ashish.githubissues.Pojo

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

class Label : Serializable {
    @SerializedName("id")
    var id: Long? = null

    @SerializedName("node_id")
    var nodeId: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("color")
    var color: String? = null

    @SerializedName("default")
    var default: Boolean? = null

    companion object {
        private const val serialVersionUID = -6488379377378348892L
    }
}