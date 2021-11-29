package com.test.ashish.githubissues


import com.test.ashish.githubissues.Pojo.Issues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ResultActivity : AppCompatActivity() {

    private var issuesViewModel: IssuesViewModel? = null
    private var allIssues: List<Issues>? = null
    private var orgName: String? = ""
    private var repoName: String? = ""
    var attempt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        issuesViewModel =  ViewModelProvider(this).get(IssuesViewModel::class.java)

        issuesViewModel!!.deleteAll(Date().time - 30 * 60 * 1000)
        if (intent != null && intent.getStringExtra("org_name") != null) {
            orgName = intent.getStringExtra("org_name")
            repoName = intent.getStringExtra("repo_name")
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        allIssues = ArrayList<Issues>()
        val noteAdapter = IssuesAdapter(this, allIssues as ArrayList<Issues>)
        recyclerView.adapter = noteAdapter
        issuesViewModel!!.currentStatus!!.observe(this, { aBoolean ->
            if (aBoolean == false) {
                Toast.makeText(this@ResultActivity, "No Match Found", Toast.LENGTH_LONG).show()
            }
        })
        issuesViewModel!!.getAllIssues(orgName, repoName)!!.observe(this, androidx.lifecycle.Observer {issues->
            if (issues!!.size > 0) {
                noteAdapter.resetList(issues)
            }
        })
    }
}