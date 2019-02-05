package com.test.ashish.githubissues;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.test.ashish.githubissues.Pojo.Issues;

@Database(entities = Issues.class,version = 2, exportSchema = false)
public abstract class IssuesDatabase extends RoomDatabase {

    private static IssuesDatabase instance;

    public abstract IssuesDao issuesDao();

    public static synchronized IssuesDatabase getInstance(Context context){
        if(instance ==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), IssuesDatabase.class, "issues_database")
                    .addCallback(mCallBack)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback mCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}