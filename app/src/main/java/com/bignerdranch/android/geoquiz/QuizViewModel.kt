package com.bignerdranch.android.geoquiz

import android.util.Log  // imports the following
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"  // creates a TAG variable for

class QuizViewModel : ViewModel() {

    var currentIndex = 0  // initializes currentIndex variable that holds an Int of 0

    private val questionBank = listOf(  // initializes questionBank list that holds question string reference and answer values
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    val currentQuestionAnswer: Boolean  // returns answer for current question
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int  // returns current question text
        get() = questionBank[currentIndex].textResID

    fun moveToNext() {  // defines function moveToNext which allows user to advance 1 question
        currentIndex = (currentIndex + 1) % questionBank.size // sets currentIndex variable to computed value
    }

    fun moveToLast() {  // defines function moveToLast which allows user to go back 1 question
        if (currentIndex >= 1) {
            currentIndex = (currentIndex - 1) % questionBank.size  // sets currentIndex variable to computed value
        } else {   // user can't go back before the first question (index 0)
            currentIndex = (currentIndex + 1) % questionBank.size
        }
    }
}


