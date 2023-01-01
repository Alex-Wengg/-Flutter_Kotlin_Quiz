package com.example.quizz_app

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

class QuestionRepository {

    private QuestinoDao questionDao;
    private LiveData<List<Question>> getQuestions;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    QuestionRepository(Application application) {
        QuestionDatabase db = QuestionDatabase.getDatabase(application);
        questionDao = db.questionDao();
        getQuestions = mWordDao.getQuestions();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Question>> getQuestions() {
        return getQuestions;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Question question) {
        QuestionDatabase.databaseWriteExecutor.execute(() -> {
            questionDao.insert(question);
        });
    }
}