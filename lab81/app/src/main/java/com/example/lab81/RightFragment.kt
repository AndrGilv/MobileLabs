package com.example.lab81

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random


class RightFragment(val operation: Int = 1) : Fragment() {

    private var answersList: IntArray = IntArray(4)
    private lateinit var trueWrongAnswersCounter: TrueWrongAnswersCounter
    private var answer: Int = 0
    private var expression: String = "Выражение"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_right, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        if(savedInstanceState != null){
            answersList = savedInstanceState.getIntArray("answersList") ?: IntArray(0)
            answer = savedInstanceState.getInt("answer")
            expression = savedInstanceState.getString("question") ?: "Выражение"
            view.findViewById<TextView>(R.id.expressionTextView).text = expression
        }
        else{
            view.initQuestion()
        }

        val adapter = AnswersListAdapter(answersList) { index: Int ->
            if (answersList[index].equals(answer)) {
                trueWrongAnswersCounter.incrementRightAnswerCount()
                trueWrongAnswersCounter.riseNumberLimitIfNeeded()
            } else {
                trueWrongAnswersCounter.incrementWrongAnswerCount()
            }
            view.initQuestion()
            recyclerView.adapter!!.notifyDataSetChanged()
        }
        recyclerView.adapter = adapter
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        trueWrongAnswersCounter = context as TrueWrongAnswersCounter
    }

    fun View.initQuestion() {
        val numberLimit = trueWrongAnswersCounter.getNumberLimit()
        val num1 = Random.nextInt(-numberLimit, numberLimit)
        val num2 = Random.nextInt(-numberLimit, numberLimit)
        val expressionTextView = findViewById<TextView>(R.id.expressionTextView)
        answer = when (operation) {
            0 -> {
                expression = "$num1 * $num2 = ?"
                expressionTextView.text = expression
                num1 * num2
            }
            1 -> {
                expression = "$num1 + $num2 = ?"
                expressionTextView.text = expression
                num1 + num2
            }
            2 -> {
                expression = "$num1 - $num2 = ?"
                expressionTextView.text = expression
                num1 - num2
            }
            else -> {
                Toast.makeText(this.context, "something want wrong", Toast.LENGTH_SHORT).show()
                0
            }
        }

        answersList[0] = answer
        for (i in (1..3)) {
            answersList[i] = answer + Random.nextInt(-numberLimit, numberLimit)
        }
        answersList.shuffle()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("answersList", answersList)
        outState.putInt("answer", answer)
        outState.putString("question", expression)
    }
}