package com.bignerdranch.android.geoquiz

import android.os.Bundle  // imports the following
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders.*

private const val TAG = "MainActivity"  // adds a TAG const to MainActivity
private const val KEY_INDEX = "index"  // initializes KEY_INDEX key with the listed string value

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button  // initializes the listed variables and their type
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {  // lazy allows for quizViewModel to be a val.  QuizViewModel initialized in lazy mode so it doesn't run it until it is triggered by a quizViewModel function call
        of(this)[QuizViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {  // calls the onCreate function that allows for savedInstanceStates using Bundles
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")  // logCat logging
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0  // resets currentIndex to the value saved in the key_index bundle
        quizViewModel.currentIndex = currentIndex  // restores the currentIndex value from quizViewModel

        trueButton = findViewById(R.id.true_button)  // ties the listed variables to the listed resource IDs
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        /*questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }*/

        trueButton.setOnClickListener {  // sets OnClickListener to trueButton
            checkAnswer(true)  // calls check answer function with true user answer boolean
        }

        falseButton.setOnClickListener {
            checkAnswer(false)  // calls check answer function with false user answer boolean
        }

        nextButton.setOnClickListener {  // sets OnClickListener to nextButton
            quizViewModel.moveToNext()  // calls moveToNext function in quizViewModel
            updateQuestion()  // calls updateQuestion function
        }

        previousButton.setOnClickListener {  // sets OnClickListener to previousButton
            quizViewModel.moveToLast()  // calls moveToLast function in quizViewModel
            updateQuestion()  // calls updateQuestion function
            }

        updateQuestion()  // calls updateQuestion function

    }

    override fun onStart() {  // logCat logging for onStart
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {  // logCat logging for onResume
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {  // calls the onSaveInstanceState function which passes the Key_Index value to the quizViewModel.currentIndex variable
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)  // passes the value as in Int data type
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {  // defines updateQuestion function
        val questionTextResId = quizViewModel.currentQuestionText  // initializes questionTextRedId variable that gets a reference for the TextView and sets its text to the question at the currentIndex value in the line below
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {  // defines the checkAnswer function that takes a userAnswer Boolean parameter
        val correctAnswer = quizViewModel.currentQuestionAnswer  // initializes correctAnswer variable that holds the correct answer boolean value from the questionBank

        val messageResID = if (userAnswer == correctAnswer) {  // initializes messageResId variable the calls and if -else statement and calls the listed Toasts
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show()  // calls toast
        }
}


