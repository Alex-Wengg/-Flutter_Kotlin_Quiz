package com.example.quizz_app

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Insert
    suspend fun insertChoices(choices: List<Choice>)

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Delete
    suspend fun deleteChoices(choices: List<Choice>)

    @Query("SELECT * FROM Question")
    fun getQuestions(): Flow<List<Question>>

    @Query("SELECT * FROM Choice WHERE QuestionId = :Qid")
    fun getChoices(Qid: Int): List<Choice>
}