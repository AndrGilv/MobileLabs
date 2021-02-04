package com.example.lab3

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var randomNumbersList: MutableList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            randomNumbersList = ArrayList(savedInstanceState.getIntArray("integerNumber")!!.toList())
            for(item in randomNumbersList){
                addTextView(item)
            }
            findViewById<ScrollView>(R.id.scrollView).verticalScrollbarPosition = savedInstanceState.getInt("scrollPosition")
        }
        else{
            randomNumbersList = mutableListOf()
        }
    }

    fun buttonAddClick(view: View) {
        val number: Int = Random.nextInt(0, 100)
        randomNumbersList.add(number)
        addTextView(number)
    }

    fun addTextView(number: Int) {
        val textView = TextView(this)
        textView.text = number.toString()//viewsCount.toString()
        textView.textSize= 32f
        val container = findViewById<LinearLayout>(R.id.innerContainer)
        container.addView(textView)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("integerNumber", randomNumbersList.toIntArray())
        val scrollPosition = findViewById<ScrollView>(R.id.scrollView).verticalScrollbarPosition
        outState.putInt("scrollPosition", scrollPosition)
    }

}

