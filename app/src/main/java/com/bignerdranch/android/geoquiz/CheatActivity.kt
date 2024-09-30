package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown" // key for holding the CheatActivity intent's extra to send back to MainActivity
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true" // key for holding the intent's extra

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView  // initializes answerTextView: TextView variable
    private lateinit var showAnswerButton: Button  // initializes showAnswerButton: Button variable

    private var answerIsTrue = false  // initializes answerIsTrue variable that holds boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)  // sets the answerIsTrue variable to a packaged intent that holds the key constant EXTRA_ANSWER_IS_TRUE and the boolean value false.
        answerTextView = findViewById(R.id.answer_text_view)  // ties answerTextView to listed resource id
        showAnswerButton = findViewById(R.id.show_answer_button)  // ties showAnswerButton to the listed resource id

        showAnswerButton.setOnClickListener {  // setOnClickListener tied to showAnswerButton.  Displays True or False based on the answerIsTrue intent that is passed
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)  // sets answerTextView to answerText
            setAnswerShownResult(true)  // calls setAnswerShownResult function
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {  // defines setAnswerShownResult function that takes isAnswerShown: Boolean data type argument
        val data = Intent().apply {  // initializes a data value that stores an Intent key-value pair
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)  // when user presses back button to return to MainActivity the ActivityManager calls request code from MainActivity and the result code and intent passed in to setResult
    }

    companion object {  // creates a companion object that allows you to access functions without having an instance of a class
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {  // defines newIntent function that packages the answerIsTrue Boolean value with the EXTRA_ANSWER_IS_TRUE key constant
            return Intent(packageContext, CheatActivity::class.java).apply {  // returns intent from packageContext to CheatActivity
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }


}