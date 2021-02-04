package com.example.lab8

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RightFragment(Fruit: Int= 0) : Fragment() {
    private val fruit = Fruit
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            when (fruit){
                1 -> R.layout.fragment_orange
                2 -> R.layout.fragment_watermelon
                else -> R.layout.fragment_cherry
                        },container, false)}}