package com.example.lab81

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity() : AppCompatActivity(), TrueWrongAnswersCounter, OperationChanger/*, AnswerListItemOnClickListener */{

    private var operation: Int = 1
    private var rightAnswers: Int = 0
    private var wrongAnswers: Int = 0
    private var numberLimit: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null){
            operation = savedInstanceState.getInt("operation")
            rightAnswers = savedInstanceState.getInt("rightAnswers")
            wrongAnswers = savedInstanceState.getInt("wrongAnswers")
            numberLimit = savedInstanceState.getInt("numberLimit")
        }
        else{
            supportFragmentManager.beginTransaction()
                    .add(R.id.frame_left, LeftFragment())
                    .add(R.id.frame_right, RightFragment(operation))
                    .commit()
        }

        findViewById<TextView>(R.id.rightAnswersCountTextView).text = "Правильных ответов: $rightAnswers"
        findViewById<TextView>(R.id.wrongAnswersCountTextView).text = "Неверных ответов: $wrongAnswers"



    }

    override fun changeOperation(operation: Int) {
        this.operation = operation
        supportFragmentManager.beginTransaction()
                .replace( R.id.frame_right , RightFragment(operation))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit()
    }

    override fun getWrongAnswerCount(): Int = wrongAnswers
    override fun getRightAnswerCount(): Int = rightAnswers

    override fun incrementWrongAnswerCount() {
        wrongAnswers++
        findViewById<TextView>(R.id.wrongAnswersCountTextView).text = "Неверных ответов: $wrongAnswers"
    }
    override fun incrementRightAnswerCount() {
        rightAnswers++
        findViewById<TextView>(R.id.rightAnswersCountTextView).text = "Правильных ответов: $rightAnswers"
    }

    override fun riseNumberLimitIfNeeded() {
        if(rightAnswers % 2 == 0){
            numberLimit *= 10
        }
    }

    override fun getNumberLimit(): Int {
        return numberLimit
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("operation", operation)
        outState.putInt("rightAnswers", rightAnswers)
        outState.putInt("wrongAnswers", wrongAnswers)
        outState.putInt("numberLimit", numberLimit)
    }
}