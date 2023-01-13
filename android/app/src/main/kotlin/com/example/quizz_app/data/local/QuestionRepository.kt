package com.example.quizz_app

import android.app.Application;
import androidx.lifecycle.LiveData;
import kotlinx.coroutines.flow.Flow
import androidx.annotation.WorkerThread

import java.util.List;

class QuestionRepository(private val questionDao: QuestionDao)  {

    // private val questionDao: QuestionDao ? = null ;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    // suspend fun QuestionRepository(Application: application) {


    //     val QuestionDatabase: db = QuestionDatabase.getInstance(application);
    //     questionDao = db.questionDao();
    //     getQuestions = mWordDao.getQuestions();
    // }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    // suspend fun getQuestions(): kotlin.collections.List<Question>{
    //     return  questionDao.getQuestions();
    // }

    val getQuestions: Flow<kotlin.collections.List<Question>> = questionDao.getQuestions()

    val getThemeQuestions: Flow<kotlin.collections.List<Question>> = questionDao.getQuestions()

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(question: Question): Long? {
        // QuestionDatabase.databaseWriteExecutor.execute(Runnable  {

            return questionDao.insertQuestion(question);
        // });
    }

        @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getThemeQuestions(text: String): Flow<kotlin.collections.List<Question>>  {
        // QuestionDatabase.databaseWriteExecutor.execute(Runnable  {

            return questionDao.getThemeQuestions(text);
        // });
    }
    
}