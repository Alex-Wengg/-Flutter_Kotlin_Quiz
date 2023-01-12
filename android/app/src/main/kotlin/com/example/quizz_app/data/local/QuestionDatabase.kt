package com.example.quizz_app


import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [Choice::class, Question::class],
    version = 1
)
abstract class QuestionDatabase : RoomDatabase() {
//https://developer.android.com/codelabs/android-room-with-a-view-kotlin#0
    abstract fun QuestionDao(): QuestionDao

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
                ).build()
            INSTANCE = instance
            // return instance
            instance
        }
    }
    suspend fun populateDatabase(wordDao: WordDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            questionDao.deleteAll()
            var question = Question("","")
            var choice = Choice("", true)
            var qid = 0

            question = Question("kubernetes", "API object that manages external access to the services in a cluster, typically HTTP.")
           
            qid =  questionDao.insertQuestion(question)

            choice = Choice("Ingress", true, qid)
            questiondao.insertChoices(choice)
            choice = Choice("Progress", false, qid)
            questiondao.insertChoices(choice)
            choice = Choice("Regress", false, qid)
            questiondao.insertChoices(choice)


        }
    }
}