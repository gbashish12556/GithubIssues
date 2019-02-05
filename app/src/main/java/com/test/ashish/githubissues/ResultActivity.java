package com.test.ashish.githubissues;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.test.ashish.githubissues.Pojo.Issues;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private IssuesViewModel issuesViewModel;
    private List<Issues> allIssues;
    private String orgName = "";
    private String repoName = "";
    int attempt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        issuesViewModel = (IssuesViewModel) ViewModelProviders.of(this).get(IssuesViewModel.class);
//        issuesViewModel.deleteAll((new Date()).getTime());
        issuesViewModel.deleteAll((new Date()).getTime()-30*60*1000);

        if(getIntent() != null && getIntent().getStringExtra("org_name") != null){
            orgName = getIntent().getStringExtra("org_name");
            repoName = getIntent().getStringExtra("repo_name");
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allIssues = new ArrayList<>() ;

        final IssuesAdapter noteAdapter = new IssuesAdapter(this, allIssues);

        recyclerView.setAdapter(noteAdapter);


        issuesViewModel.getAllIssues(orgName,repoName).observe(this,new Observer<List<Issues>>() {
            @Override
            public void onChanged(@Nullable List<Issues> issues) {
                //Data Changed
                if(issues.size()>0) {
                    noteAdapter.resetList(issues);
                }else{
                    if(attempt == 0) {
                        attempt++;
                        issuesViewModel.fetchApi(orgName, repoName);
                    }else{
                        Log.d("noMatch","noMatch");
                        Toast.makeText(ResultActivity.this,"No Match Found", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}
