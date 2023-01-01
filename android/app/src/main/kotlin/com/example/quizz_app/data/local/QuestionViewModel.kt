package com.example.quizz_app

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionViewModel extends AndroidViewModel {

   private QuestionRepository mRepository;

   private final LiveData<List<Question>> mAllWords;

   public QuestionViewModel (Application application) {
       super(application);
       mRepository = new QuestionRepository(application);
       mAllWords = mRepository.getQuestions();
   }

   LiveData<List<Question>> getQuestions() { return mAllWords; }

   public void insert(Question word) { mRepository.insert(word); }
}