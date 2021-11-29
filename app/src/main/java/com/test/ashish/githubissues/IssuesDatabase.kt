package com.test.ashish.githubissues

import com.test.ashish.githubissues.Pojo.Issues
import kotlin.jvm.Synchronized
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Issues::class], version = 2, exportSchema = false)
abstract class IssuesDatabase : RoomDatabase() {
    abstract fun issuesDao(): IssuesDao?

    companion object {
        private var instance: IssuesDatabase? = null
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): IssuesDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    IssuesDatabase::class.java,
                    "issues_database"
                )
                    .addCallback(mCallBack)
                    .fallbackToDestructiveMigration().build()
            }
            return instance
        }

        private val mCallBack: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}