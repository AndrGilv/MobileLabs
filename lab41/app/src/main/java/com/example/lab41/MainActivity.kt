package com.example.lab41

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var answersList: IntArray = IntArray(4)
    private var answer: Int = 0

    private var rightAnswers: Int = 0
    private var wrongAnswers: Int = 0

    private var numberLimit: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.rightAnswersCountTextView).text = "Правильных ответов: $rightAnswers"
        findViewById<TextView>(R.id.wrongAnswersCountTextView).text = "Неверных ответов: $wrongAnswers"

        initQuestion()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val adapter = AnswersListAdapter(answersList) { index: Int ->
            if (answersList[index].equals(answer)) {
                rightAnswers++
                findViewById<TextView>(R.id.rightAnswersCountTextView).text = "Правильных ответов: $rightAnswers"
                if(rightAnswers % 2 == 0){
                    numberLimit *= 10
                }
            }
            else{
                wrongAnswers++
                findViewById<TextView>(R.id.wrongAnswersCountTextView).text = "Неверных ответов: $wrongAnswers"
            }
            initQuestion()
            recyclerView.adapter!!.notifyDataSetChanged()
        }

        recyclerView.adapter = adapter
    }

    fun initQuestion(){
        val num1 = Random.nextInt(-numberLimit, numberLimit)
        val num2 = Random.nextInt(-numberLimit, numberLimit)
        val expressionTextView = findViewById<TextView>(R.id.expressionTextView)

        answer = when(Random.nextInt(1, 4)) {
            1 ->{
                expressionTextView.text = "$num1 + $num2 = ?"
                num1 + num2
            }
            2 ->{
                expressionTextView.text = "$num1 - $num2 = ?"
                num1 - num2
            }
            3 ->{
                expressionTextView.text = "$num1 * $num2 = ?"
                num1 * num2
            }
            else ->{
                Toast.makeText(this,"something want wrong", Toast.LENGTH_SHORT).show()
                0
            }
        }

        answersList[0] = answer
        for (i in (1..3)){
            answersList[i] = answer + Random.nextInt(-numberLimit, numberLimit)
        }
        answersList.shuffle()
    }
}