package com.example.quizz_app

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope;
import androidx.lifecycle.asLiveData

import kotlinx.coroutines.launch

import java.util.List;

class QuestionViewModel(private val repository: QuestionRepository) : ViewModel() {


    // private val mAllWords: LiveData<List<Question>> = repository.getQuestions().asLiveData() ;

      val getQuestions: kotlin.collections.List<Question> = 
       
            repository.getQuestions; 
    // Launching a new coroutine to insert the data in a non-blocking way

    fun  insert(question: Question) = 
        viewModelScope.launch  { 
            repository.insert(question); 
        }
}