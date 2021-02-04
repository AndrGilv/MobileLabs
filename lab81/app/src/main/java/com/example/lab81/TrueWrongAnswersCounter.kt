package com.example.lab81

interface TrueWrongAnswersCounter {

    fun getWrongAnswerCount(): Int
    fun getRightAnswerCount(): Int

    fun incrementWrongAnswerCount()
    fun incrementRightAnswerCount()

    fun riseNumberLimitIfNeeded()
    fun getNumberLimit(): Int
}