package com.example.quizz_app


import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import android.util.Log

@Database(
    entities = [Choice::class, Question::class],
    version = 4, exportSchema = false
)
abstract class QuestionDatabase : RoomDatabase() {
//https://developer.android.com/codelabs/android-room-with-a-view-kotlin#0
    abstract fun questionDao(): QuestionDao

    companion object {
        // Singleton prevents multiple instances of database  
        @Volatile
        private var INSTANCE: QuestionDatabase? = null

        @Synchronized
        fun getInstance(context: Context, 
        scope: CoroutineScope): QuestionDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        QuestionDatabase::class.java, 
                        "Question"
                    )
                    .fallbackToDestructiveMigration()
                    .addCallback(QuestionDatabaseCallback(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
        private class QuestionDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.i("TAG", "INSIDE")
                Log.i("TAG", "OUTSIDE")

                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.questionDao())
                    }
                }
            }
        }
    
        suspend fun populateDatabase(questionDao: QuestionDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.


            //questionDao.deleteAll()
            var question = Question("","","","","","",1)
            var choice = Choice("", true)
            var qid:Int = 0

            question = Question(
                "kubernetes", 
                "API object that manages external access to the services in a cluster, typically HTTP.",
                "Ingress",
                "Progress",
                "Regress",
                "Frontend API",
                4)
            questionDao.insertQuestion(question)

            

        }
    }
}