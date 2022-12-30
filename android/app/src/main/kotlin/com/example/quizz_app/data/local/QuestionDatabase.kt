package com.example.quizz_app


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Choice::class, Question::class],
    version = 1
)
abstract class QuestionDatabase : RoomDatabase() {

    abstract fun QuestionDao(): QuestionDao
}