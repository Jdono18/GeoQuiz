package com.bignerdranch.android.geoquiz

import android.os.Bundle  // imports the following
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button  // initializes the listed variable and their type
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var previousButton: Button
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(  // initializes questionBank list that holds question string reference and answer values
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0  // initializes currentIndex variable that holds an Int of 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)  // ties the listed variables to the listed resource IDs
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)


        trueButton.setOnClickListener {  // sets OnClickListener to trueButton
            checkAnswer(true)  // calls check answer function with true user answer boolean
        }

        falseButton.setOnClickListener {
            checkAnswer(false)  // calls check answer function with false user answer boolean
        }

        nextButton.setOnClickListener {  // sets OnClickListener to nextButton
            currentIndex = (currentIndex + 1) % questionBank.size  // sets currentIndex variable to computed value
            updateQuestion()  // calls updateQuestion function
        }

        previousButton.setOnClickListener {  // sets OnClickListener to previousButton
            currentIndex = (currentIndex + 1) % questionBank.size  // sets currentIndex variable to computed value
            updateQuestion()  // calls updateQuestion function
        }

        updateQuestion()  // calls updateQuestion function
    }

    private fun updateQuestion() {  // defines updateQuestion function
        val questionTextResId = questionBank[currentIndex].textResID  // initializes questionTextRedId variable that gets a reference for the TextView and sets its text to the question at the currentIndex value in the line below
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {  // defines the checkAnswer function that takes a userAnswer Boolean parameter
        val correctAnswer = questionBank[currentIndex].answer  // initializes correctAnswer variable that holds the correct answer boolean value from the questionBank

        val messageResID = if (userAnswer == correctAnswer) {  // initializes messageResId variable the calls and if -else statement and calls the listed Toasts
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show()  // calls toast 
        }
    }

