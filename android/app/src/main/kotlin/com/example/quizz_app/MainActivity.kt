package com.example.quizz_app

import kotlin.random.Random
import androidx.annotation.NonNull
import android.content.res.Configuration
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.util.Log
import android.content.Context
import com.google.gson.Gson


class MainActivity: FlutterActivity() {

    // private var qdb: QuestionDatabase = QuestionDatabase.getInstance(this);



 

  //override the configureFlutterEngine from FlutterActivity to register a 
  //method channel for building a communication line with Dart.
  override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
    var qdb: QuestionDatabase = async { QuestionDatabase.getInstance(this)}.await();
    // Log.d("TAG", "today's message message");
    // Log.d("TAG", (Gson().toJson(qdb.QuestionDao().getQuestions())));
    doAsync

    super.configureFlutterEngine(flutterEngine)
    MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "example.com/channel").setMethodCallHandler {
      call, result ->
        if(call.method == "getRandomNumber") {
          val rand = Random.nextInt(100)
          result.success(rand)
        } else if (call.method == "test") {
          val questionList = Gson().toJson(setData.getQuestions())

          // questionList=setData.getQuestions()
          Log.d("TAG", "today's message message");

          Log.d("TAG",  questionList);

          result.success(questionList)
        }else {
          result.notImplemented()
        }
    }
  }
}