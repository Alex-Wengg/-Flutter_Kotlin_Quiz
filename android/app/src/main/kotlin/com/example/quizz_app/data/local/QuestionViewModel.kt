package com.example.quizz_app

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewModelScope;
import androidx.lifecycle.asLiveData
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.launch

import java.util.List;

class QuestionViewModel(private val repository: QuestionRepository) : ViewModel() {


    // private val mAllWords: LiveData<List<Question>> = repository.getQuestions().asLiveData() ;

      val getQuestions: Flow<kotlin.collections.List<Question>> = 
       
            repository.getQuestions; 
    // Launching a new coroutine to insert the data in a non-blocking way

    fun  insert(question: Question) = 
        viewModelScope.launch  { 
            repository.insert(question); 
        }
}
class QuestionViewModelFactory(private val repository: QuestionRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuestionViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}