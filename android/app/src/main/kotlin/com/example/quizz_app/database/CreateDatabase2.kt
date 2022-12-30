// package com.example.quizz_app;

// import android.content.ContentValues
// import android.content.Context
// import android.database.Cursor
// import android.database.sqlite.SQLiteDatabase
// import android.database.sqlite.SQLiteOpenHelper
// import android.util.Log


//  class PostsDatabaseHelper(context: Context) :
//     SQLiteOpenHelper(context,   DATABASE_NAME, null, DATABASE_VERSION) {
//     // Database Info
//         companion object {  
//     private val DATABASE_NAME: String = "quiz_database";
//     private val DATABASE_VERSION: Int = 1;
//         }
//     // Table Names
    
//     private val TABLE_QUESTIONS: String = "questions";
//     private val TABLE_CHOICES: String = "choices";
//     private val TABLE_USERS: String = "users";

//     // Questions Table Columns
//     private val QUESTION_ID: String = "id";
//     private val QUESTION_TEXT: String = "text";
//     private val QUESTION_TITLE: String = "title";

//     // Choice Table Columns
//     private val CHOICE_ID: String = "id";
//     private val CHOICE_TEXT: String = "text";
//     private val CHOICE_ANSWER: String = "answer";
//     private val QUESTION_ID_FK: String = "questionId";


//     // Called when the database connection is being configured.
//     // Configure database settings for things like foreign key support, write-ahead logging, etc.
//     @Override
//     override fun onConfigure(db: SQLiteDatabase ) {
//         super.onConfigure(db);
//         db.setForeignKeyConstraintsEnabled(true);
//     }

//     // Called when the database is created for the FIRST time else not called
//     @Override
//     override fun onCreate(db: SQLiteDatabase ) {

//         var  CREATE_QUESTIONS_TABLE: String = "CREATE TABLE " + TABLE_QUESTIONS +
//                 " (" +
//                     QUESTION_ID + " INTEGER PRIMARY KEY, " + // Define a primary key
//                     QUESTION_TEXT + " TEXT NOT NULL, " +
//                     QUESTION_TITLE + " TEXT NOT NULL, " +
//                 ") ";

//         var  CREATE_CHOICE_TABLE: String = "CREATE TABLE " + TABLE_CHOICES +
//                 " (" +
//                     CHOICE_ID + " INTEGER PRIMARY KEY, " +
//                     CHOICE_TEXT + " TEXT NOT NULL, " +
//                     CHOICE_ANSWER + " BOOLEAN NOT NULL, " +
//                     "FOREIGN KEY (" + QUESTION_ID_FK + ") INTEGER REFERENCE " + TABLE_QUESTIONS + " (" + QUESTION_ID + " )" // Define a foreign key
//                 " )";

//         db.execSQL(TABLE_QUESTIONS);
//         db.execSQL(TABLE_CHOICES);
//     }
    
//     // Called when the database needs to be upgraded.
//     //  if a database NAME exists but DATABASE_VERSION != database that exists on disk.
//     @Override
//     override fun onUpgrade(db: SQLiteDatabase , oldVersion: Int, newVersion: Int ) {
//         if (oldVersion != newVersion) {
//             // Simplest implementation is to drop all old tables and recreate them
//             db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
//             db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHOICES);
//             onCreate(db);
//         }
//     }

//     // Insert a post into the database
//     fun addPost(question: Question ){
//         // Create and/or open the database for writing
//         var db: SQLiteDatabase = getWritableDatabase();

//         // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
//         // consistency of the database.
//         db.beginTransaction();
//         try {

//             var questionData: ContentValues = ContentValues();
//             questionData.put(QUESTION_TITLE, question.title);
//             questionData.put(QUESTION_TEXT, question.text);
//             var questionid: Long  = db.insertOrThrow(TABLE_QUESTIONS, null, questionData);

//             var choiceData: ContentValues =  ContentValues();
//             for ((key, value) in question.choices) {
//               questionData.put(CHOICE_TEXT, key);
//               questionData.put(CHOICE_ANSWER, value);
//               questionData.put(QUESTION_ID_FK, questionid);
//               db.insertOrThrow(TABLE_CHOICES, null, choiceData);
//             }
//             db.setTransactionSuccessful();
//         } catch (e: Exception) {
//             Log.d("TAG", "Error while trying to add post to database");
//         } finally {
//             db.endTransaction();
//         }
//         return ;
//     }

//     // Get all posts in the database
//     fun getAllPosts(): ArrayList<Question> {
//         var questions: ArrayList<Question> = ArrayList<Question>();
//         var db: SQLiteDatabase = getReadableDatabase();
 
//         var GET_QUESTIONS: String =
//                 String.format("SELECT * FROM %s",
//                         TABLE_QUESTIONS);
//         var questionCursor: Cursor = db.rawQuery(GET_QUESTIONS, null);
//         var title: String;
//         var text: String;
//         var questionId: String;
//         var hashMap: HashMap<String, Boolean>;
//         try {
//             if (questionCursor.moveToFirst()) {
//                 do {
//                     title = questionCursor.getString(questionCursor.getColumnIndex(QUESTION_TITLE));
//                     text = questionCursor.getString(questionCursor.getColumnIndex(QUESTION_TEXT));
//                     questionId = questionCursor.getString(questionCursor.getColumnIndex(QUESTION_ID))
                    
//                     // make this a function later
//                     var GET_CHOICES: String =
//                                 String.format("SELECT * FROM %s WHERE %s.%s = ",
//                                         TABLE_CHOICES, TABLE_CHOICES, QUESTION_ID_FK, questionId);
//                     var choiceCursor: Cursor = db.rawQuery(GET_QUESTIONS, null);
//                     hashMap = HashMap<String, Boolean> ();
//                     if (choiceCursor.moveToFirst()) {
//                         do {
//                           var choiceText: String = choiceCursor.getString(choiceCursor.getColumnIndex(CHOICE_TEXT));
//                           var choiceAnswer: Boolean = choiceCursor.getInt(choiceCursor.getColumnIndex(CHOICE_ANSWER)) == 1;
//                           hashMap.put(choiceText, choiceAnswer)
//                         } while(choiceCursor.moveToNext());
//                     }

//                     questions.add(Question(title, text, hashMap));
//                 } while(questionCursor.moveToNext());
//             }
//         } catch (e: Exception) {
//             Log.d("TAG", "Error while trying to get posts from database");
//         } finally {
//             if (questionCursor != null && !questionCursor.isClosed()) {
//                 questionCursor.close();
//             }
//         }
//         return questions;
//     }

//     // Delete all database
//     fun purgeAll(){
//         var db: SQLiteDatabase  = getWritableDatabase();
//         db.beginTransaction();
//         try {
//             // Order of deletions is important when foreign key relationships exist.
//             db.delete(TABLE_QUESTIONS, null, null);
//             db.delete(TABLE_CHOICES, null, null);

//             db.delete(TABLE_USERS, null, null);
//             db.setTransactionSuccessful();
//         } catch (e: Exception) {
//             Log.d("TAG", "Error while trying to delete all posts and users");
//         } finally {
//             db.endTransaction();
//         }
//     }    
// }