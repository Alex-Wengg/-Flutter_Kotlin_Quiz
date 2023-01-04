package com.example.quizz_app

import kotlin.random.Random
import androidx.annotation.NonNull
import android.content.res.Configuration
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragmentActivity

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.util.Log
import android.content.Context
import com.google.gson.Gson
import androidx.activity.viewModels


class MainActivity:  FlutterFragmentActivity() {

    // private var qdb: QuestionDatabase = QuestionDatabase.getInstance(this);

    private val questionViewModel: QuestionViewModel by viewModels {
        QuestionViewModelFactory((application as QuestionApplication).repository)
    }

  //override the configureFlutterEngine from FlutterActivity to register a 
  //method channel for building a communication line with Dart.
  override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
    // var qdb: QuestionDatabase = async { QuestionDatabase.getInstance(this)}.await();
  

    super.configureFlutterEngine(flutterEngine)
    MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "example.com/channel").setMethodCallHandler {
      call, result ->
        if(call.method == "getRandomNumber") {
          val rand = Random.nextInt(100)
          result.success(rand)
        } else if (call.method == "test") {
          val questionList = Gson().toJson(setData.getQuestions())

  Log.d("TAG", "today's what how message");
   val que1 = Question("",
          "Can our insertion work?"
        )
          // kotlin.collections.hashMapOf("1" to false, 
          //           "3" to false, 
          //           "12" to true,
          //           "5" to false)
 
    Log.d("SSTAG", (questionViewModel.insert(que1)).toString());
    Log.d("TAG", (Gson().toJson(questionViewModel.getQuestions)));
    var save = 0;
    questionViewModel.id.observe(this) {
    Log.d("questionViewModel.id.value", (questionViewModel.id.value).toString());
     questionViewModel.id.value
    }

          // questionList=setData.getQuestions()
          // Log.d("TAG", "today's message message");

          // Log.d("TAG",  questionList);

          result.success(questionList)
        }else {
          result.notImplemented()
        }
    }
  }
}