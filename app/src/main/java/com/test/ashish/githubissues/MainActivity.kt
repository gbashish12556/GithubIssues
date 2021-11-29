package com.test.ashish.githubissues

import butterknife.BindView
import com.test.ashish.githubissues.R
import android.widget.EditText
import android.os.Bundle
import butterknife.ButterKnife
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.test.ashish.githubissues.ResultActivity

class MainActivity : AppCompatActivity() {
    @BindView(R.id.orgName)
    lateinit var orgNameEditText: EditText

    @BindView(R.id.repoName)
    lateinit var  repoNameEditText: EditText

    @BindView(R.id.fetchButton)
    lateinit var  fetchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        fetchButton!!.setOnClickListener {
            val orgName = orgNameEditText.text.toString()
            val repoName = repoNameEditText.text.toString()
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("org_name", orgName)
            intent.putExtra("repo_name", repoName)
            startActivity(intent)
        }

    }
}