package com.test.ashish.githubissues;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.orgName)
    EditText orgNameEditText;

    @BindView(R.id.repoName)
    EditText repoNameEditText;

    @BindView(R.id.fetchButton)
    Button fetchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String orgName = orgNameEditText.getText().toString();
                String repoName = repoNameEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("org_name", orgName);
                intent.putExtra("repo_name",repoName);
                startActivity(intent);
            }
        });
    }
}
