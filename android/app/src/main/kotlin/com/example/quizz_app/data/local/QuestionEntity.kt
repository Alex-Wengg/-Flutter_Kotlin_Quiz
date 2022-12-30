package com.example.quizz_app 

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded
import androidx.room.Relation

@Entity()
data class Question(
    var category: String,
    var text: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null

)

@Entity()
data class Choice(
    val text: String,
    val answer: Boolean,
    val questionId: Int? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)

@Entity()
data class ChoiceList(
    @Embedded val question: Question,
    @Relation(
          parentColumn = "id",
          entityColumn = "questionId"
    )
    val chocies: List<Choice>
)