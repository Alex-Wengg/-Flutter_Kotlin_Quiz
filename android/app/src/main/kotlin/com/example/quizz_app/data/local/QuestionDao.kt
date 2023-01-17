package com.example.quizz_app

import androidx.lifecycle.MediatorLiveData 
import androidx.lifecycle.LiveData

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question): Long

    @Insert
    suspend fun insertChoice(choice: Choice): Long

    @Insert
    suspend fun insertChoices(choices: List<Choice>)

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Delete
    suspend fun deleteChoices(choices: List<Choice>)

    @Query("SELECT * FROM Question ORDER BY id DESC")
    fun getQuestions(): Flow<List<Question>>

    @Query("SELECT * FROM Question WHERE category = :Theme")
    fun getThemeQuestions(Theme: String): Flow<List<Question>>

    @Query("SELECT * FROM Choice WHERE QuestionId = :Qid")
    fun getChoices(Qid: Int): Flow<List<Choice>>
}