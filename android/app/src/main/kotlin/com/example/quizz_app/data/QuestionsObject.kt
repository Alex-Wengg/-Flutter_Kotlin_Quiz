package com.example.quizz_app

// import com.example.model.Question

object setData {

    const val name:String="name"
    const val score:String="score"

    // TODO (STEP 1 to Create a constant variables which we required in the result screen.)
    // START
    // const val USER_NAME to String = "user_name"
    // const val TOTAL_QUESTIONS to String = "total_questions"
    // const val CORRECT_ANSWERS to String = "correct_answers"
    // END
    
    fun getQuestions(): ArrayList<QuestionModel> {

        val questionsList = ArrayList<QuestionModel>()

        // // 1
        val que1 = QuestionModel("",
          "How Many Whiskers does the average cat have on each side of its face ?",
          hashMapOf("1" to false, 
                    "3" to false, 
                    "12" to true,
                    "5" to false)
        )
        questionsList.add(que1)

        // 2
        val que2 = QuestionModel("",
          "When does a cat purr ?",
          hashMapOf(
            "When it cares for its kittens" to false,
            "When it needs confort" to false,
            "When it feels content" to false,
            "All of the above" to true,
          )
        )

        questionsList.add(que2)

        // 3
        val que3 = QuestionModel("",
          "What is the averge nulber of kittens in a litter ?",
          hashMapOf(
            "1 to 2" to false,
            "3 to 5" to true,
            "8 to 10" to false,
            "12 to 14" to false,
          )
        )

        questionsList.add(que3)

        // 4
        val que4 = QuestionModel("",
            "How many moons does Mars have ?",
            hashMapOf(
              "1" to false,
              "2" to false,
              "4" to true,
              "8" to false,
            )
        )

        questionsList.add(que4)

        // 5
        val que5 = QuestionModel("",
          "What is Mars's nickname ?",
          hashMapOf(
            "The red planet" to true,
            "The dusty planet" to false,
            "The hot planet" to false,
            "The smelly planet" to false,
          )  
        )

        questionsList.add(que5)

          // 6
        val que6 = QuestionModel("",
          "About How long would it take to travel to Mars ?",
          hashMapOf(
            "Three days" to false,
            "A month" to false,
            "Eight months" to true,
            "Two years" to false,
          )  
        )

        questionsList.add(que6)

        // 7
        val que7 = QuestionModel("",
          "Mars is Named after the Roman god Mars. What is he the god of ?",
          hashMapOf(
            "War" to true,
            "Love" to false,
            "Agriculture" to false,
            "Fire" to false,
          )  
        )

        questionsList.add(que7)

        // 8
        val que8 = QuestionModel("",
          "Mars Is the ___ planet from the sun ?",
          hashMapOf(
            "Fourth" to true,
            "Second" to false,
            "Third" to false,
            "Fifth" to false,
          )  
        )

        questionsList.add(que8)

        // 9
        val que9 = QuestionModel("",
          "Where did Orville and Wilbur Wright build their first flying airplane ?",
          hashMapOf(
            "United States" to false,
            "Soviet Union (now Russia)" to true,
            "China" to false,
            "Rocketonia" to false
          )  
        )

        questionsList.add(que9)

        //10
        val que10 = QuestionModel("",
      "The First astronuts to travel to space came from which country ?",
          hashMapOf(
            "Kitty Hawk, North Carolina" to true,
            "Paris, France" to false,
            "Boston, Massachusetts" to false,
            "Tokyo, Japan" to false
          )  
        )

        questionsList.add(que10)

        return questionsList
    }
}