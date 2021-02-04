package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var count: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun myButtonClick(view: View) {
        // Получаем первое число
        val edit1: EditText = findViewById(R.id.number1)
        val n1 = edit1.toFloat()
        // Получаем второе число
        val edit2: EditText = findViewById(R.id.number2)
        val n2 = edit2.toFloat()
        // Помещаем результат в текстовое поле
        val n= n1 + n2
        val textView: TextView = findViewById(R.id.result)
        val resText = resources.getString(R.string.operation_result)
        textView.text = String.format(resText, n)
    }

    fun subtractionButtonClick(view: View) {
        // Получаем первое число
        val edit1: EditText = findViewById(R.id.number1)
        val n1 = edit1.toFloat()
        // Получаем второе число
        val edit2: EditText = findViewById(R.id.number2)
        val n2 = edit2.toFloat()
        // Помещаем результат в текстовое поле
        val n= n1 - n2
        val textView: TextView = findViewById(R.id.result)
        val resText = resources.getString(R.string.operation_result)
        textView.text = String.format(resText, n)
    }

    fun divideButtonClick(view: View) {
        // Получаем первое число
        val edit1: EditText = findViewById(R.id.number1)
        val n1 = edit1.toFloat()
        // Получаем второе число
        val edit2: EditText = findViewById(R.id.number2)
        val n2 = edit2.toFloat()
        // Помещаем результат в текстовое поле
        val textView: TextView = findViewById(R.id.result)
        if(!n2.equals(0f)){
            val n = n1 / n2
            val resText = resources.getString(R.string.operation_result)
            textView.text = String.format(resText, n)
        }
        else{
            textView.text = resources.getString(R.string.infinite_result)
        }
    }

    fun multiplyButtonClick(view: View) {
        // Получаем первое число
        val edit1: EditText = findViewById(R.id.number1)
        val n1 = edit1.toFloat()
        // Получаем второе число
        val edit2: EditText = findViewById(R.id.number2)
        val n2 = edit2.toFloat()
        // Помещаем результат в текстовое поле
        val n= n1 * n2
        val textView: TextView = findViewById(R.id.result)
        val resText = resources.getString(R.string.operation_result)
        textView.text = String.format(resText, n)
    }

    fun modButtonClick(view: View) {
        // Получаем первое число
        val edit1: EditText = findViewById(R.id.number1)
        val n1 = edit1.toFloat()
        // Получаем второе число
        val edit2: EditText = findViewById(R.id.number2)
        val n2 = edit2.toFloat()
        // Помещаем результат в текстовое поле
        val textView: TextView = findViewById(R.id.result)
        if(!n2.equals(0f)){
            val n = n1 % n2
            val resText = resources.getString(R.string.operation_result)
            textView.text = String.format(resText, n)
        }
        else{
            textView.text = resources.getString(R.string.infinite_result)
        }
    }
    
    fun EditText.toFloat(): Float{
        val str : String = this.text.toString()
        return if (str.isEmpty() ) 0f else str.toFloat()
    }

}